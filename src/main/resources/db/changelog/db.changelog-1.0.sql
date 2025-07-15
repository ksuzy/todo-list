--liquibase formatted sql

--changeset ksuzy:1
CREATE TABLE IF NOT EXISTS task
(
    id BIGSERIAL PRIMARY KEY ,
    title VARCHAR(255) NOT NULL,
    is_done BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deadline TIMESTAMP
    );
