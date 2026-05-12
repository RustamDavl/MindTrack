package ru.rstd.mtrack.core.security.repository.mapper;

import org.springframework.stereotype.Component;
import ru.rstd.mtrack.core.common.mapper.Mapper;
import ru.rstd.mtrack.core.security.repository.entity.UserEntityRole;
import ru.rstd.mtrack.core.security.model.user.UserRole;

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
