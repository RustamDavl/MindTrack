--liquibase formatted sql

--changeset rustdv:20260313-01-create-users-table
CREATE TABLE IF NOT EXISTS mtrack.users
(
    id             UUID PRIMARY KEY                  DEFAULT pg_catalog.gen_random_uuid(),
    name           VARCHAR(255),
    email          VARCHAR(255)             NOT NULL UNIQUE,
    password_hash  VARCHAR(255),
    email_verified BOOLEAN                  NOT NULL DEFAULT false,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITH TIME ZONE NOT NULL
);
-- rollback DROP TABLE mtrack.users

--changeset rustdv:20260313-02-create-user_role-table
CREATE TABLE IF NOT EXISTS mtrack.user_role
(
    id         UUID PRIMARY KEY DEFAULT pg_catalog.gen_random_uuid(),
    user_id    UUID REFERENCES mtrack.users (id) ON DELETE CASCADE NOT NULL,
    name       VARCHAR(255)             NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    UNIQUE (user_id, name)
);
-- rollback DROP TABLE mtrack.user_role