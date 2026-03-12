package ru.rstd.mindly.application.config.liquibase;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class LiquibaseSchemaConfig implements BeanPostProcessor {

    private static final String CREATE_SCHEMA_SQL = """
            CREATE SCHEMA IF NOT EXISTS %s
            """;

    @Value("${spring.liquibase.default-schema}")
    private String defaultSchema;

    @Value("${spring.liquibase.liquibase-schema}")
    private String liquibaseSchema;

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (bean instanceof DataSource dataSource) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement prs1 = connection.prepareStatement(CREATE_SCHEMA_SQL.formatted(defaultSchema));
                 PreparedStatement prs2 = connection.prepareStatement(CREATE_SCHEMA_SQL.formatted(liquibaseSchema))) {
                prs1.execute();
                prs2.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
