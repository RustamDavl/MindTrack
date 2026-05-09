package ru.rstd.mtrack.kafka.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "ru.rstd.mtrack.kafka.service"
})
public class MtrackKafkaModuleConfiguration {

    @Bean
    public MtrackConsumerConfig mtrackConsumerConfig() {
        return new MtrackConsumerConfig();
    }

    @Bean
    public MtrackProducerConfig mtrackProducerConfig() {
        return new MtrackProducerConfig();
    }
}
