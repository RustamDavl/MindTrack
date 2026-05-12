package ru.rstd.mtrack.core.mail.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mtrack.mail")
public class AppMailProperties {
    private String mailConfirmationUrl;
}
