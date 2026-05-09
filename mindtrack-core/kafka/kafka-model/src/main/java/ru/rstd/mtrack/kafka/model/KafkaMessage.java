package ru.rstd.mtrack.kafka.model;


public record KafkaMessage(String topic, String key, Object payload) {
}
