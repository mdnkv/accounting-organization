BEGIN;

CREATE TABLE IF NOT EXISTS organizations (
    id UUID PRIMARY KEY,
    organization_name VARCHAR(255) NOT NULL
);

COMMIT;