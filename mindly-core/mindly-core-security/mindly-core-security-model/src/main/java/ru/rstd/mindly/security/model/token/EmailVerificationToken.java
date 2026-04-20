package ru.rstd.mindly.security.model.token;

import lombok.Data;
import ru.rstd.mindly.common.model.base.BaseModel;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class EmailVerificationToken extends BaseModel {
    private UUID userId;
    private String token;
    private OffsetDateTime expiresAt;
}
