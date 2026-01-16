CREATE TYPE metric_type_enum AS ENUM (
    'WEIGHT',
    'BODY_FAT'
);

CREATE TYPE metric_source_enum AS ENUM (
    'MANUAL',
    'DEVICE'
);

CREATE TABLE user_metrics (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    type metric_type_enum NOT NULL,
    value NUMERIC(5,2) NOT NULL CHECK (value > 0),
    source metric_source_enum NOT NULL DEFAULT 'MANUAL',
    recorded_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_user_metrics_user
      FOREIGN KEY (user_id)
          REFERENCES users(id)
          ON DELETE CASCADE
);

