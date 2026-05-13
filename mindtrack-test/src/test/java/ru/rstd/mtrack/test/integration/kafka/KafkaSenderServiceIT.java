package ru.rstd.mtrack.test.integration.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.rstd.mtrack.core.kafka.model.KafkaMessage;
import ru.rstd.mtrack.core.kafka.model.KafkaResponse;
import ru.rstd.mtrack.core.kafka.service.api.KafkaSenderService;
import ru.rstd.mtrack.test.integration.IntegrationTestBase;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

public class KafkaSenderServiceIT extends IntegrationTestBase {
    private static final String EMAIL_VERIFICATION_TOPIC = "email_verification_message_event";
    private static final String EMAIL_VERIFICATION_DLT_TOPIC = EMAIL_VERIFICATION_TOPIC + "-dlt";

    @Autowired
    private KafkaSenderService kafkaSenderService;

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Test
    void send_sync_should_return_success_when_message_is_sent() {
        KafkaMessage message = new KafkaMessage(
                "send-sync-happy-case-" + UUID.randomUUID(),
                "test-key",
                Map.of("value", "test-payload")
        );

        KafkaResponse response = kafkaSenderService.sendSync(message);

        Assertions.assertThat(response.isSuccess()).isTrue();
        Assertions.assertThat(response.message()).isNull();
    }

    @Test
    void send_sync_should_return_error_when_topic_is_invalid() {
        KafkaMessage message = new KafkaMessage(
                "invalid topic name",
                "test-key",
                Map.of("value", "test-payload")
        );

        KafkaResponse response = kafkaSenderService.sendSync(message);

        Assertions.assertThat(response.isSuccess()).isFalse();
        Assertions.assertThat(response.message()).isNotBlank();
    }

    @Test
    void send_sync_should_publish_message_to_dlt_when_listener_cannot_handle_payload() {
        String messageKey = "dlt-test-key-" + UUID.randomUUID();
        KafkaMessage message = new KafkaMessage(
                EMAIL_VERIFICATION_TOPIC,
                messageKey,
                Map.of("unexpected", "payload")
        );

        try (KafkaConsumer<String, String> dltConsumer = createDltConsumer()) {
            dltConsumer.subscribe(Collections.singleton(EMAIL_VERIFICATION_DLT_TOPIC));

            KafkaResponse response = kafkaSenderService.sendSync(message);
            Optional<String> dltPayload = findDltPayload(dltConsumer, messageKey);

            Assertions.assertThat(response.isSuccess()).isTrue();
            Assertions.assertThat(dltPayload).isPresent();
            Assertions.assertThat(dltPayload.get()).contains("unexpected", "payload");
        }
    }

    private KafkaConsumer<String, String> createDltConsumer() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-sender-service-it-" + UUID.randomUUID());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return new KafkaConsumer<>(properties);
    }

    private Optional<String> findDltPayload(KafkaConsumer<String, String> consumer, String messageKey) {
        long deadline = System.currentTimeMillis() + Duration.ofSeconds(15).toMillis();
        while (System.currentTimeMillis() < deadline) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(250));
            for (ConsumerRecord<String, String> record : records.records(EMAIL_VERIFICATION_DLT_TOPIC)) {
                if (messageKey.equals(record.key())) {
                    return Optional.ofNullable(record.value());
                }
            }
        }
        return Optional.empty();
    }
}
