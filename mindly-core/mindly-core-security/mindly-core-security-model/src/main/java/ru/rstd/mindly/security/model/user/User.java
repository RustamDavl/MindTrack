package ru.rstd.mindly.security.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rstd.mindly.common.model.audit.Auditable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class User extends Auditable {

    public User() {}

    public User(UUID id,
                OffsetDateTime createdAt,
                OffsetDateTime updatedAt,
                String email,
                Boolean isEmailVerified,
                String name,
                String passwordHash,
                List<UserRole> roles) {
        super(id, createdAt, updatedAt);
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.name = name;
        this.passwordHash = passwordHash;
        this.roles = roles;
    }

    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordHash;
    private Boolean isEmailVerified;
    private List<UserRole> roles;
}
