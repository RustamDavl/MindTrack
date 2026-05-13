package ru.rstd.mtrack.core.kafka.service.api;

import ru.rstd.mtrack.core.kafka.model.KafkaMessage;
import ru.rstd.mtrack.core.kafka.model.KafkaResponse;

public interface KafkaSenderService {
    void sendAsync(KafkaMessage message);
    KafkaResponse sendSync(KafkaMessage message);
}
