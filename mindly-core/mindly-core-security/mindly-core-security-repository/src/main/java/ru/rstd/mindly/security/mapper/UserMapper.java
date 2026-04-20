package ru.rstd.mindly.security.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rstd.mindly.common.model.mapper.Mapper;
import ru.rstd.mindly.security.entity.UserEntity;
import ru.rstd.mindly.security.entity.UserEntityRole;
import ru.rstd.mindly.security.model.user.User;
import ru.rstd.mindly.security.model.user.UserRole;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<UserEntity, User> {
    private final UserRoleMapper userRoleMapper;

    @Override
    public UserEntity toEntity(User model) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(model.getId());
        userEntity.setName(model.getName());
        userEntity.setEmail(model.getEmail());
        userEntity.setPasswordHash(model.getPasswordHash());
        userEntity.setEmailVerified(model.getIsEmailVerified());
        List<UserEntityRole> roles = model
                .getRoles()
                .stream()
                .map(userRoleMapper::toEntity).toList();
        userEntity.addRoles(roles);
        return userEntity;
    }

    @Override
    public User toModel(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setPasswordHash(entity.getPasswordHash());
        user.setIsEmailVerified(entity.getEmailVerified());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());

        List<UserRole> roles = entity
                .getRoles()
                .stream()
                .map(userRoleMapper::toModel)
                .toList();

        user.setRoles(roles);
        return user;
    }
}
