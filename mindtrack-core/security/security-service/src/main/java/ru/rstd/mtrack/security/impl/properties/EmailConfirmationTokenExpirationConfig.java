package ru.rstd.mtrack.security.impl.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mtrack.security.mail")
public class EmailConfirmationTokenExpirationConfig {
    private Duration expirationDuration;
}
