package ru.rstd.mtrack.core.kafka.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "ru.rstd.mtrack.core.kafka.service"
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
