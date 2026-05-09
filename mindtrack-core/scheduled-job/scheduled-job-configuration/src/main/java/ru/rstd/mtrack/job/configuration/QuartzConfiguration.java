package ru.rstd.mtrack.job.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.rstd.mtrack.job.service.properties.QuartzDataSourceProperties;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"ru.rstd.mtrack.job.service"})
@RequiredArgsConstructor
public class QuartzConfiguration {
    private final QuartzDataSourceProperties quartzDataSourceProperties;

    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(quartzDataSourceProperties.getDataSource().getUrl());
        hikariConfig.setUsername(quartzDataSourceProperties.getDataSource().getUsername());
        hikariConfig.setPassword(quartzDataSourceProperties.getDataSource().getPassword());
        hikariConfig.setDriverClassName(quartzDataSourceProperties.getDataSource().getDriverClassName());
        return new HikariDataSource(hikariConfig);
    }
}
