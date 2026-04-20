package ru.rstd.mindly.security.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mindly.common.model.mapper.Mapper;
import ru.rstd.mindly.security.entity.UserEntityRole;
import ru.rstd.mindly.security.model.user.UserRole;

@Component
public class UserRoleMapper implements Mapper<UserEntityRole, UserRole> {

    @Override
    public UserEntityRole toEntity(UserRole model) {
        UserEntityRole entity = new UserEntityRole();
        entity.setId(model.getId());
        entity.setName(model.getName());
        return entity;
    }

    @Override
    public UserRole toModel(UserEntityRole entity) {
        return new UserRole(entity.getName());
    }
}
