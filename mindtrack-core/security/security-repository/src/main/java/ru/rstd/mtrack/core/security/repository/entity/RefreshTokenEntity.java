package ru.rstd.mtrack.core.security.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.rstd.mtrack.core.common.entity.BaseEntity;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(schema = "mtrack", name = "refresh_token")
public class RefreshTokenEntity extends BaseEntity {
    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "expires_at")
    private OffsetDateTime expiresAt;

    @Column(name = "is_revoked")
    private Boolean isRevoked;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}
