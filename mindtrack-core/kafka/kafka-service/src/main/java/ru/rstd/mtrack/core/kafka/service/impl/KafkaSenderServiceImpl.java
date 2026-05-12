package ru.rstd.mtrack.core.kafka.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.kafka.api.KafkaSenderService;
import ru.rstd.mtrack.core.kafka.model.KafkaMessage;

@Service
public class KafkaSenderServiceImpl implements KafkaSenderService {
    private final KafkaTemplate<String, Object> producerTemplate;

    public KafkaSenderServiceImpl(@Qualifier("producerTemplate") KafkaTemplate<String, Object> producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @Override
    public void send(KafkaMessage message) {
        if(message.key() == null) {
            producerTemplate.send(message.topic(), message.payload());
        }
        else {
            producerTemplate.send(message.topic(), message.key(), message.payload());
        }
    }
}
