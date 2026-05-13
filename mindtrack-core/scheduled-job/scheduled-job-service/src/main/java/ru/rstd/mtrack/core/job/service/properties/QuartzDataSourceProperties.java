package ru.rstd.mtrack.core.job.service.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mtrack.job.quartz")
public class QuartzDataSourceProperties {
    private QuartzDataSource dataSource = new QuartzDataSource();

    @Data
    public static class QuartzDataSource {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
        private Hikari hikari = new Hikari();

        @Data
        private static class Hikari {
            private String poolName;
            private Integer maximumPoolSize;
        }
    }
}
