--liquibase formatted sql

--changeset rustdv:20260107-01-create-folder-table
CREATE TABLE IF NOT EXISTS mindly.folder
(
    id         UUID PRIMARY KEY DEFAULT pg_catalog.gen_random_uuid(),
    name       VARCHAR(256),
    color      VARCHAR(128),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);
-- rollback DROP TABLE mindly.folder

--changeset rustdv:20260107-02-create-note-table
CREATE TABLE IF NOT EXISTS mindly.note
(
    id         UUID PRIMARY KEY DEFAULT pg_catalog.gen_random_uuid(),
    folder_id  UUID references mindly.folder (id),
    title      VARCHAR(256),
    body       TEXT,
    color      VARCHAR(128),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);
-- rollback DROP TABLE mindly.note

--changeset rustdv:20260107-03-create-tag-table
CREATE TABLE IF NOT EXISTS mindly.tag
(
    id    UUID PRIMARY KEY DEFAULT pg_catalog.gen_random_uuid(),
    name  VARCHAR(256),
    color VARCHAR(128)
);
-- rollback DROP TABLE mindly.tag

--changeset rustdv:20260107-04-create-note_tag-table
CREATE TABLE IF NOT EXISTS mindly.note_tag
(
    id      UUID PRIMARY KEY DEFAULT pg_catalog.gen_random_uuid(),
    note_id UUID REFERENCES note (id) ON DELETE CASCADE,
    tag_id  UUID REFERENCES tag (id) ON DELETE CASCADE,
    UNIQUE (note_id, tag_id)
);
-- rollback DROP TABLE mindly.note_tag

