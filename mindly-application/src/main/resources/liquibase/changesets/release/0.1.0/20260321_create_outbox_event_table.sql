--liquibase formatted sql

--changeset rustdv:20260321-01-create-outbox_status-enum
CREATE TYPE mindly.outbox_status AS ENUM (
    'NEW',
    'IN_PROGRESS',
    'PROCESSED',
    'FAILED'
    );
-- rollback DROP TYPE mindly.outbox_status

--changeset rustdv:20260321-02-create-outbox_event-table
CREATE TABLE IF NOT EXISTS mindly.outbox_event
(
    id                 UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    event_type         VARCHAR(255)             NOT NULL,
    payload            JSONB                    NOT NULL,
    status             mindly.outbox_status     NOT NULL DEFAULT 'NEW',
    attempts           INT                      NOT NULL DEFAULT 0,
    processed_at       TIMESTAMP WITH TIME ZONE,
    last_error_message TEXT,
    created_at         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);
-- rollback DROP TABLE mindly.outbox_event