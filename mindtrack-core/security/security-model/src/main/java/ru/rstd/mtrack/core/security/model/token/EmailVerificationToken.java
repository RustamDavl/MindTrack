package ru.rstd.mtrack.core.security.model.token;

import lombok.Data;
import ru.rstd.mtrack.core.common.model.base.BaseModel;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class EmailVerificationToken extends BaseModel {
    private UUID userId;
    private String token;
    private OffsetDateTime expiresAt;
}
