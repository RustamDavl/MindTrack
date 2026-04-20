package ru.rstd.mindly.security.model.user;

public record UserRequest(
        String name,
        String email,
        String rawPassword
) {
    public UserRequest(String email, String password) {
        this(null, email, password);
    }
}
