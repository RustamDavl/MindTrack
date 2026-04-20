package ru.rstd.mindly.security.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rstd.mindly.common.model.audit.Auditable;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserRole extends Auditable {
    private String name;

    public UserRole() {}

    public UserRole(String name) {
        this.name = name;
    }

    public UserRole(UUID id,
                    OffsetDateTime createdAt,
                    OffsetDateTime updatedAt,
                    String name) {
        super(id, createdAt, updatedAt);
        this.name = name;
    }
}
