package ru.rstd.mtrack.core.security.model.user;

public record UserWithEmailToken(User user, String token) {
}
