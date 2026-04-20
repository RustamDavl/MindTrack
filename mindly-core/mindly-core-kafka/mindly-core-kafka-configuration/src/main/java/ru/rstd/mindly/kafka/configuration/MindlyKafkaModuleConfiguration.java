package ru.rstd.mindly.kafka.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "ru.rstd.mindly.kafka.service"
})
public class MindlyKafkaModuleConfiguration {

    @Bean
    public MindlyConsumerConfig mindlyConsumerConfig() {
        return new MindlyConsumerConfig();
    }

    @Bean
    public MindlyProducerConfig mindlyProducerConfig() {
        return new MindlyProducerConfig();
    }
}
