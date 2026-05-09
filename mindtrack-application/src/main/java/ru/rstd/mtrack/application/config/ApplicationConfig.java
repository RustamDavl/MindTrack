package ru.rstd.mtrack.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.rstd.mtrack.job.configuration.QuartzConfiguration;
import ru.rstd.mtrack.kafka.configuration.MtrackKafkaModuleConfiguration;
import ru.rstd.mtrack.mail.configuration.MailConfig;
import ru.rstd.mtrack.outbox.configuration.OutboxConfiguration;
import ru.rstd.mtrack.security.configuration.SecurityConfiguration;


@Configuration
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "ru.rstd.mtrack.repository.jpa")
@EntityScan(basePackages = {
        "ru.rstd.mtrack.common.entity",
        "ru.rstd.mtrack.repository.entity"
})
@ComponentScan(basePackages = {
        "ru.rstd.mtrack.service",
        "ru.rstd.mtrack.rest",
        "ru.rstd.mtrack"
})
@Import({
        SecurityConfiguration.class,
        QuartzConfiguration.class,
        OutboxConfiguration.class,
        MtrackKafkaModuleConfiguration.class,
        MailConfig.class
})
public class ApplicationConfig {
}