package ru.rstd.mtrack.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.rstd.mtrack.core.job.configuration.QuartzConfiguration;
import ru.rstd.mtrack.core.kafka.configuration.MtrackKafkaModuleConfiguration;
import ru.rstd.mtrack.core.mail.configuration.MailConfig;
import ru.rstd.mtrack.core.outbox.configuration.OutboxConfiguration;
import ru.rstd.mtrack.core.security.configuration.SecurityConfiguration;


@Configuration
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "ru.rstd.mtrack.core.repository.jpa")
@EntityScan(basePackages = {
        "ru.rstd.mtrack.core.common.entity",
        "ru.rstd.mtrack.core.repository.entity"
})
@ComponentScan(basePackages = {
        "ru.rstd.mtrack.core.service",
        "ru.rstd.mtrack.core.rest",
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