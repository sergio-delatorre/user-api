DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS phones CASCADE;


CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255) NOT NULL,
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