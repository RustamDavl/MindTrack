package ru.rstd.mtrack.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.rstd.mtrack.common.entity.BaseEntity;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(schema = "mtrack", name = "email_verification_token")
public class EmailVerificationTokenEntity extends BaseEntity {

    @Column(name = "token_hash")
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "expires_at")
    private OffsetDateTime expiresAt;
}
