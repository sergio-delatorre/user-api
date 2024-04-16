DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS phones CASCADE;


CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    token UUID NOT NULL,
    created TIMESTAMP,
    modified TIMESTAMP
    );

CREATE TABLE phones (
    id UUID PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    city_code VARCHAR(10) NOT NULL,
    country_code VARCHAR(10) NOT NULL,
    user_id UUID,
    created TIMESTAMP ,
    modified TIMESTAMP
    );

ALTER TABLE phones
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES users(id) ON DELETE CASCADE;


INSERT INTO users (id, name, email, password, token, created, modified) VALUES
    ('d3a9a9f1-eb11-4fd4-9f5d-dbd579dd16d1', 'Usuario 1', 'usuario1@example.com', 'password1', 'e98c8e53-697e-488b-8b7b-134c8f23e1c4', CURRENT_TIMESTAMP, NULL);

INSERT INTO users (id, name, email, password, token, created, modified) VALUES
    ('9baa161a-66ab-494e-8162-5f916e4e1a51', 'Usuario 2', 'usuario2@example.com', 'password2', '352daa35-f17b-4e22-b4e4-d9a9c0db75b8', CURRENT_TIMESTAMP, NULL);

INSERT INTO phones (id, number, city_code, country_code, user_id, created, modified) VALUES
    ('89e46f57-4f14-4687-b720-93b9af48c3a2', '123456789', '123', '456', 'd3a9a9f1-eb11-4fd4-9f5d-dbd579dd16d1', CURRENT_TIMESTAMP, NULL);

INSERT INTO phones (id, number, city_code, country_code, user_id, created, modified) VALUES
    ('f1db85db-8ba7-4cf5-b0b7-53a837ab28e4', '987654321', '987', '654', '9baa161a-66ab-494e-8162-5f916e4e1a51', CURRENT_TIMESTAMP, NULL);

INSERT INTO phones (id, number, city_code, country_code, user_id, created, modified) VALUES
    ('4a8b49dc-3b4f-42af-8b82-c7ef7b62504f', '987654321', '987', '654', '9baa161a-66ab-494e-8162-5f916e4e1a51', CURRENT_TIMESTAMP, NULL);