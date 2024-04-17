INSERT INTO users (id, name, email, password
                , token, created, modified) VALUES
    ('d3a9a9f1-eb11-4fd4-9f5d-dbd579dd16d1', 'Usuario 1', 'usuario1@example.com', 'password1'
    , 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzEzMzczMDIwLCJleHAiOjE3MTMzNzY2MjB9.nOptypJiuvByM1Qgfjdesy6jGUy4ylOBMmp23ICU1aY', CURRENT_TIMESTAMP, NULL);

INSERT INTO users (id, name, email, password
                , token, created, modified) VALUES
    ('9baa161a-66ab-494e-8162-5f916e4e1a51', 'Usuario 2', 'usuario2@example.com', 'password2'
    , 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzEzMzczMTc5LCJleHAiOjE3MTMzNzY3Nzl9.0VulIeSdmjYcFD2Z8aWnhfFdw8BdF_O_Lo0zZcbRV9A', CURRENT_TIMESTAMP, NULL);

INSERT INTO phones (id, number, city_code, country_code, user_id, created, modified) VALUES
    ('89e46f57-4f14-4687-b720-93b9af48c3a2', '123456789', '123', '456', 'd3a9a9f1-eb11-4fd4-9f5d-dbd579dd16d1', CURRENT_TIMESTAMP, NULL);

INSERT INTO phones (id, number, city_code, country_code, user_id, created, modified) VALUES
    ('f1db85db-8ba7-4cf5-b0b7-53a837ab28e4', '987654321', '987', '654', '9baa161a-66ab-494e-8162-5f916e4e1a51', CURRENT_TIMESTAMP, NULL);

INSERT INTO phones (id, number, city_code, country_code, user_id, created, modified) VALUES
    ('4a8b49dc-3b4f-42af-8b82-c7ef7b62504f', '987654321', '987', '654', '9baa161a-66ab-494e-8162-5f916e4e1a51', CURRENT_TIMESTAMP, NULL);