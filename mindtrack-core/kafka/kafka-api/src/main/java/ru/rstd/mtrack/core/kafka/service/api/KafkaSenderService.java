package ru.rstd.mtrack.core.kafka.service.api;

import ru.rstd.mtrack.core.kafka.model.KafkaMessage;

public interface KafkaSenderService {
    void send(KafkaMessage message);
}
