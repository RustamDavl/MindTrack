package ru.rstd.mtrack.security.model.user;

public record UserWithEmailToken(User user, String token) {
}
