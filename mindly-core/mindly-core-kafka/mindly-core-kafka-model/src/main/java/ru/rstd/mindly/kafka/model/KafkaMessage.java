package ru.rstd.mindly.kafka.model;


public record KafkaMessage(String topic, String key, Object payload) {
}
