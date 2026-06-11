package ru.rstd.mtrack.core.security.model.token;


import org.springframework.http.ResponseCookie;

public record AccessWithRefreshToken(String accessToken, ResponseCookie responseRefreshTokenCookie) {
}
