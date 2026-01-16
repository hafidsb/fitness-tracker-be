CREATE TYPE gender_enum AS ENUM (
    'MALE',
    'FEMALE',
    'OTHER'
);

CREATE TYPE user_status_enum AS ENUM (
    'ACTIVE',
    'SUSPENDED',
    'DELETED'
);

CREATE TABLE users (
    id UUID PRIMARY KEY,
    full_name VARCHAR(255),
    gender gender_enum,
    birth_date DATE,
    height_cm SMALLINT CHECK (height_cm > 0),
    current_weight_kg NUMERIC(5,2) CHECK (current_weight_kg > 0),
    status user_status_enum NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
