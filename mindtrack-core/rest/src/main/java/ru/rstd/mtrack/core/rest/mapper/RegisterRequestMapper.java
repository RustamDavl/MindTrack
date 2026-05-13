package ru.rstd.mtrack.core.rest.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mtrack.core.rest.dto.RegisterRequest;
import ru.rstd.mtrack.core.security.model.user.UserRequest;


@Component
public class RegisterRequestMapper implements CreateMapper<RegisterRequest, UserRequest> {

    @Override
    public UserRequest toModel(RegisterRequest registerRequest) {
        return new UserRequest(registerRequest.getName(), registerRequest.getEmail(), registerRequest.getPassword());
    }
}
