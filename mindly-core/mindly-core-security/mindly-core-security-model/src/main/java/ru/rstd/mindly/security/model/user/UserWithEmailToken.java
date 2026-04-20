package ru.rstd.mindly.security.model.user;

public record UserWithEmailToken(User user, String token) {
}
