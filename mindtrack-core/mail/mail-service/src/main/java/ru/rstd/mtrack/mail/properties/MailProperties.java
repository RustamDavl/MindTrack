package ru.rstd.mtrack.mail.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {
    private String port;
    private String host;
    private String username;
    private String password;
    private Properties properties;
}
