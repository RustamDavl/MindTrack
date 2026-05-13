package ru.rstd.mtrack.core.kafka.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.kafka.model.KafkaMessage;
import ru.rstd.mtrack.core.kafka.model.KafkaResponse;
import ru.rstd.mtrack.core.kafka.service.api.KafkaSenderService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaSenderServiceImpl implements KafkaSenderService {
    private final KafkaTemplate<String, Object> producerTemplate;

    public KafkaSenderServiceImpl(@Qualifier("producerTemplate") KafkaTemplate<String, Object> producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @Override
    public void sendAsync(KafkaMessage message) {
        validate(message);
        CompletableFuture<SendResult<String, Object>> completableFuture =
                producerTemplate.send(message.topic(), message.key(), message.payload());
    }

    @Override
    public KafkaResponse sendSync(KafkaMessage message) {
        validate(message);
        return getResult(message);
    }

    private void validate(KafkaMessage message) {
        if (message == null) {
            throw new RuntimeException("kafka message is null");
        }
    }

    private KafkaResponse getResult(KafkaMessage message) {
        try {
            producerTemplate.send(message.topic(), message.key(), message.payload()).get();
            return new KafkaResponse(true, null);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new KafkaResponse(false, e.getMessage());
        } catch (ExecutionException | KafkaException e) {
            return new KafkaResponse(false, e.getMessage());
        }
    }
}
