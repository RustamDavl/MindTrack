package ru.rstd.mindly.security.model.token;

public record AccessWithRefreshToken(String accessToken, String refreshToken) {
}
