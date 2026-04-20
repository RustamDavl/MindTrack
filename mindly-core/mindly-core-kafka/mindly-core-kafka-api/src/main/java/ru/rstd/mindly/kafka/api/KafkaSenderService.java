package ru.rstd.mindly.kafka.api;

import ru.rstd.mindly.kafka.model.KafkaMessage;

public interface KafkaSenderService {
    void send(KafkaMessage message);
}
