package ru.rstd.mtrack.security.model.token;

public record AccessWithRefreshToken(String accessToken, String refreshToken) {
}
