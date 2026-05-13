package ru.rstd.mtrack.core.security.model.token;

public record AccessWithRefreshToken(String accessToken, String refreshToken) {
}
