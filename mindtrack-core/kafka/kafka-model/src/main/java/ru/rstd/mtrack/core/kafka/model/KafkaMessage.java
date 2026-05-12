package ru.rstd.mtrack.core.kafka.model;


public record KafkaMessage(String topic, String key, Object payload) {
}
