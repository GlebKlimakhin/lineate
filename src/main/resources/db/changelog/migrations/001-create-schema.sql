CREATE TABLE singer
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(256) unique
);

CREATE TABLE recording
(
    id           BIGSERIAL PRIMARY KEY,
    song_code    VARCHAR(32),
    title        VARCHAR(4096),
    version      VARCHAR(128),
    release_time TIMESTAMP,
    singer_id    BIGINT REFERENCES singer(id)
);

CREATE TABLE company
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(256) unique
);

CREATE TABLE copyright
(
    id BIGSERIAL PRIMARY KEY,
    company_id bigint references company(id),
    recording_id bigint references recording(id),
    price int,
    start_date TIMESTAMP,
    expiration_date TIMESTAMP
);

