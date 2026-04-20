package ru.rstd.mindly.rest.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mindly.rest.dto.LoginRequest;
import ru.rstd.mindly.security.model.user.UserRequest;

@Component
public class LoginRequestMapper implements CreateMapper<LoginRequest, UserRequest> {

    @Override
    public UserRequest toModel(LoginRequest loginRequest) {
       return new UserRequest(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
