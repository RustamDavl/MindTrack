package ru.rstd.mindly.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.rstd.mindly.job.configuration.QuartzConfiguration;
import ru.rstd.mindly.kafka.configuration.MindlyKafkaModuleConfiguration;
import ru.rstd.mindly.mail.configuration.MailConfig;
import ru.rstd.mindly.outbox.configuration.OutboxConfiguration;
import ru.rstd.mindly.security.configuration.SecurityConfiguration;


@Configuration
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "ru.rstd.mindly.repository.jpa")
@EntityScan(basePackages = {
        "ru.rstd.mindly.common.entity",
        "ru.rstd.mindly.repository.entity"
})
@ComponentScan(basePackages = {
        "ru.rstd.mindly.service",
        "ru.rstd.mindly.rest",
         "ru.rstd.mindly"
})
@Import({
        SecurityConfiguration.class,
        QuartzConfiguration.class,
        OutboxConfiguration.class,
        MindlyKafkaModuleConfiguration.class,
        MailConfig.class
})
public class ApplicationConfig {
}