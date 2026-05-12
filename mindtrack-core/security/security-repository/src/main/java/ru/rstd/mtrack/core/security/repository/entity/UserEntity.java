package ru.rstd.mtrack.core.security.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.rstd.mtrack.core.common.entity.AuditableEntity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "mtrack", name = "users")
public class UserEntity extends AuditableEntity {

    public UserEntity() {
    }

    public UserEntity(UUID id,
                      OffsetDateTime createdAt,
                      OffsetDateTime updatedAt,
                      String name, String email,
                      String passwordHash, Boolean emailVerified) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.emailVerified = emailVerified;
    }

    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<UserEntityRole> roles;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    public void addRole(UserEntityRole role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }

        roles.add(role);
        role.setUser(this);
    }

    public void addRoles(List<UserEntityRole> userRoles) {
        if (roles == null) {
            roles = new ArrayList<>();
        }

        roles.addAll(userRoles);
        userRoles.forEach(role -> role.setUser(this));
    }
}
