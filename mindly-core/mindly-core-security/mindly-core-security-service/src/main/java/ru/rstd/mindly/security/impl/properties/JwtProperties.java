package ru.rstd.mindly.security.impl.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mindly.security")
public class JwtProperties {
    private String secret;
    private String issuer;
    private Duration accessTokenTtl;
    private Duration refreshTokenTtl;
}