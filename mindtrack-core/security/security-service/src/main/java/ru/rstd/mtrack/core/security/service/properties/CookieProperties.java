package ru.rstd.mtrack.core.security.service.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "mtrack.security.cookie")
public class CookieProperties {
    private Map<String, CookieConfig> tokens = new HashMap<>();

    public CookieConfig getCookie(String token) {
        return tokens.get(token);
    }

    @Data
    public static class CookieConfig {
        private boolean httpOnly;
        private boolean secure;
        private String path;
        private Duration maxAge;
        private String domain;
        private String sameSite;
    }
}
