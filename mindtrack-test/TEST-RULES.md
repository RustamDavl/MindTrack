# Правила написания интеграционных тестов

---

# Общие принципы

## 1. Каждый PR, изменяющий поведение системы, должен быть покрыт тестами

Если PR меняет:

* бизнес-логику
* SQL-запросы
* REST API
* Kafka-события
* интеграции
* миграции
* конфигурацию
* транзакционное поведение

то должны быть тесты соответствующего уровня.

Не всегда нужен именно интеграционный тест. Иногда достаточно unit-теста.

Но если изменение затрагивает несколько слоёв приложения или внешнюю инфраструктуру — нужен интеграционный тест.

Пример:

```text
REST API -> Service -> DB -> Kafka
```

Такой сценарий лучше покрывать интеграционным тестом.

---

## 2. Класс интеграционного теста должен иметь суффикс `IT`

### Хорошо

```java
class VacancyCreationIT {
}
```

### Плохо

```java
class VacancyCreationTest {
}
```

Суффикс `Test` лучше оставлять для unit-тестов.

---

## 3. Название метода теста пишем в snake_case

### Хорошо

```java
@Test
void should_create_vacancy_when_request_is_valid() {
}
```

### Плохо

```java
@Test
void shouldCreateVacancyWhenRequestIsValid() {
}
```
---

## 4. Название теста должно описывать сценарий

### Хорошо

```java
@Test
void should_send_message_to_dlt_when_event_processing_failed() {
}
```

### Плохо

```java
@Test
void test1() {
}
```

Примеры:

```java
should_return_404_when_unit_not_found()
should_save_user_when_email_is_confirmed()
should_not_send_kafka_event_when_transaction_rolled_back()
should_move_message_to_dlt_when_retry_attempts_exceeded()
```

---

# Структура тестов

## 5. Используем подход GIVEN / WHEN / THEN

### Пример

```java
@Test
void should_create_unit_when_request_is_valid() {

    // given
    final CreateUnitRequest request =
            new CreateUnitRequest("unit-code", "Unit name");

    // when
    final ResponseEntity<UnitResponse> response =
            unitTemplate.createUnit(request);

    // then
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.CREATED);

    assertThat(response.getBody())
            .usingRecursiveComparison()
            .isEqualTo(
                    new UnitResponse("unit-code", "Unit name")
            );
}
```

---

## 6. Один тест — один основной сценарий

### Плохо

```java
@Test
void should_create_update_delete_unit() {
}
```

### Хорошо

```java
@Test
void should_create_unit_when_request_is_valid() {
}

@Test
void should_update_unit_when_unit_exists() {
}

@Test
void should_delete_unit_when_unit_exists() {
}
```

---

## 7. Тест должен быть читаемым

Хороший тест должен отвечать на вопросы:

```text
что подготовили?
что вызвали?
что ожидали?
что изменилось в системе?
```

---

# Изоляция тестов

## 8. Тесты не должны зависеть друг от друга

### Плохо

```java
@Test
void create_user() {
    // создаёт пользователя
}

@Test
void update_user() {
    // рассчитывает,
    // что пользователь уже существует
}
```

### Хорошо

```java
@Test
void should_update_user_when_user_exists() {

    // given
    final User user =
            userRepository.save(new User("user-1"));

    // when
    ...

    // then
    ...
}
```

Каждый тест сам создаёт все необходимые данные.

---

## 9. Не используем только assertTrue/assertFalse

### Плохо

```java
final List<Unit> actual =
        unitTemplate.findUnits();

assertFalse(actual.isEmpty());
```

При падении теста непонятно,
что реально пришло.

### Хорошо

```java
assertThat(actual)
        .extracting(Unit::code)
        .containsExactly("unit-1", "unit-2");
```

---

### Плохо

```java
assertTrue(response.success());
```

### Хорошо

```java
assertThat(response.getStatusCode())
        .isEqualTo(HttpStatus.OK);

assertThat(response.getBody())
        .usingRecursiveComparison()
        .isEqualTo(expected);
```

---

## 10. Проверяем не только HTTP-статус

### Плохо

```java
assertThat(response.getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);
```

### Хорошо

```java
assertThat(response.getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);

assertThat(response.getBody())
        .usingRecursiveComparison()
        .isEqualTo(
                new ErrorResponse(
                        "VALIDATION_ERROR",
                        "Unit code is required"
                )
        );
```

Интеграционный тест должен проверять контракт API.

---

## 11. Проверяем состояние БД после операции

### Пример

```java
@Test
void should_save_unit_to_database_when_request_is_valid() {

    // given
    final CreateUnitRequest request =
            new CreateUnitRequest(
                    "unit-1",
                    "Unit 1"
            );

    // when
    unitTemplate.createUnit(request);

    // then
    final UnitEntity actual =
            unitRepository.findByCode("unit-1")
                    .orElseThrow();

    assertThat(actual.getCode())
            .isEqualTo("unit-1");

    assertThat(actual.getName())
            .isEqualTo("Unit 1");
}
```

---

## 12. Проверяем rollback для транзакционных сценариев

### Пример

```java
@Test
void should_not_save_unit_when_transaction_failed() {

    // when
    catchThrowable(() ->
            service.createUnitWithFailure("unit-1")
    );

    // then
    assertThat(
            unitRepository.findByCode("unit-1")
    ).isEmpty();
}
```

---

## 13. Не завязываемся на порядок данных без необходимости

### Плохо

```java
assertThat(actual)
        .containsExactly(unit1, unit2);
```

### Хорошо

```java
assertThat(actual)
        .containsExactlyInAnyOrder(unit1, unit2);
```

Если порядок важен —
это должно быть явно видно из теста.

---

# Тестовые данные

## 14. Не используем случайные данные без необходимости

### Плохо

```java
new Data(
        UUID.randomUUID(),
        UUID.randomUUID(),
        Instant.now()
);
```

Такие тесты сложно:

* читать
* дебажить
* воспроизводить

---

### Хорошо

```java
new Data(
        UUID.fromString(
                "00000000-0000-0000-0000-000000000001"
        ),
        "sausage",
        "milk",
        Instant.parse("2024-01-01T00:00:00Z")
);
```

---

## 15. Используем понятные значения

### Плохо

```java
"user1"
"value123"
"test"
```

### Хорошо

```java
"existing-unit"
"invalid-email"
"deleted-user"
```

Данные должны помогать понимать сценарий.

---

# Инфраструктура

---

## 16. Используем Testcontainers

Для:

* PostgreSQL
* Kafka
* Redis
* OpenSearch
* RabbitMQ

### Пример

```java
@Container
static PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres:16");
```

---
# Асинхронность

## 17. Не используем Thread.sleep

### Плохо

```java
Thread.sleep(5000);
```

---

### Хорошо

```java
await()
        .atMost(Duration.ofSeconds(10))
        .untilAsserted(() -> {

            assertThat(
                    repository.findByCode("unit-1")
            ).isPresent();
        });
```

Для асинхронных сценариев используем Awaitility.

---

# Негативные сценарии

## 18. Пишем тесты не только на happy path

Проверяем:

* validation errors
* not found
* conflict
* retry
* timeout
* rollback
* forbidden
* unauthorized
* ошибки внешних систем

### Пример

```java
@Test
void should_return_409_when_unit_code_already_exists() {
}
```

---

## 19. Проверяем error contract

### Пример

```java
assertThat(response.getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);

assertThat(response.getBody().code())
        .isEqualTo("VALIDATION_ERROR");

assertThat(response.getBody().message())
        .isEqualTo("Unit code must not be blank");
```

---

## 20. Не дублируем подготовку данных

Повторяющуюся подготовку:

* выносим в helper methods
* builders
* fixtures
* test factories

---

## 21. Тест должен падать понятно

При падении должно быть сразу видно:

* что ожидалось
* что пришло реально
* какой сценарий сломался

---

# Итог

Хороший интеграционный тест:

* изолирован
* воспроизводим
* читаем
* стабилен
* проверяет поведение системы
* не зависит от окружения
* проверяет реальные контракты
* покрывает позитивные и негативные сценарии
* помогает быстро диагностировать проблему
