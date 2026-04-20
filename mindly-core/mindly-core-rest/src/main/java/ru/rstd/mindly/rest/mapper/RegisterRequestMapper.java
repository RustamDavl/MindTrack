package ru.rstd.mindly.rest.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mindly.rest.dto.RegisterRequest;
import ru.rstd.mindly.security.model.user.UserRequest;


@Component
public class RegisterRequestMapper implements CreateMapper<RegisterRequest, UserRequest> {

    @Override
    public UserRequest toModel(RegisterRequest registerRequest) {
        return new UserRequest(registerRequest.getName(), registerRequest.getEmail(), registerRequest.getPassword());
    }
}
