package ru.rstd.mindly.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.rstd.mindly.repository")
@EntityScan(basePackages = "ru.rstd.mindly.repository.entity")
@ComponentScan(basePackages = {
        "ru.rstd.mindly.repository.mapper",
        "ru.rstd.mindly.repository.dao",
        "ru.rstd.mindly.service",
        "ru.rstd.mindly.rest"
})
public class ApplicationConfig {
}
