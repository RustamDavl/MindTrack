package ru.rstd.mindly.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.rstd.mindly.common.entity.AuditableEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "mindly", name = "user_role")
public class UserEntityRole extends AuditableEntity {

    public UserEntityRole() {}

    public UserEntityRole(UUID id,
                          OffsetDateTime createdAt,
                          OffsetDateTime updatedAt,
                          UserEntity user, String name) {
        super(id, createdAt, updatedAt);
        this.user = user;
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String name;
}