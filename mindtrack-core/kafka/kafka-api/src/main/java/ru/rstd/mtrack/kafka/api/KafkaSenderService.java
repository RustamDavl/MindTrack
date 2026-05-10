package ru.rstd.mtrack.kafka.api;

import ru.rstd.mtrack.kafka.model.KafkaMessage;

public interface KafkaSenderService {
    void send(KafkaMessage message);
}
