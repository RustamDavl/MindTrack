package ru.rstd.mtrack.core.security.model.token;

import lombok.Data;
import ru.rstd.mtrack.core.common.model.base.BaseModel;
import ru.rstd.mtrack.core.security.model.user.User;

import java.time.OffsetDateTime;

@Data
public class RefreshToken extends BaseModel {
    private String token;
    private User user;
    private OffsetDateTime createdAt;
    private OffsetDateTime expiresAt;
    private Boolean isRevoked;
}
