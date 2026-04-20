--liquibase formatted sql

--changeset rustdv:20260315-01-create-email_verification_token-table
CREATE TABLE IF NOT EXISTS mindly.email_verification_token
(
    id         UUID PRIMARY KEY DEFAULT pg_catalog.gen_random_uuid(),
    token_hash VARCHAR(255)             NOT NULL UNIQUE,
    user_id    UUID REFERENCES mindly.users (id) ON DELETE CASCADE,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL
);
-- rollback DROP TABLE mindly.email_verification_token