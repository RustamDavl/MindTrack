package ru.rstd.mtrack.rest.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mtrack.rest.dto.LoginRequest;
import ru.rstd.mtrack.security.model.user.UserRequest;

@Component
public class LoginRequestMapper implements CreateMapper<LoginRequest, UserRequest> {

    @Override
    public UserRequest toModel(LoginRequest loginRequest) {
       return new UserRequest(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
