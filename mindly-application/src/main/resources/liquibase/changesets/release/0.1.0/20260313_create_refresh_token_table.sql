--liquibase formatted sql

--changeset rustdv:20260313-01-create-refresh_token-table
CREATE TABLE IF NOT EXISTS mindly.refresh_token
(
    id         UUID PRIMARY KEY DEFAULT pg_catalog.gen_random_uuid(),
    token      VARCHAR(255)             NOT NULL UNIQUE,
    user_id    UUID REFERENCES mindly.users (id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    is_revoked BOOLEAN                  NOT NULL

);
-- rollback DROP TABLE mindly.refresh_token