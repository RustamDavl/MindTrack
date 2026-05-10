package ru.rstd.mtrack.outbox.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "ru.rstd.mtrack.outbox")
@EntityScan(basePackages = {
        "ru.rstd.mtrack.outbox.entity"
})
@EnableJpaRepositories(basePackages = {
        "ru.rstd.mtrack.outbox.repository.jpa"
})
public class OutboxConfiguration {
}
