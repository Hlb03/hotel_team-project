/*
    COMMENTED SQL STATEMENTS ARE DEDICATED TO MANUAL DB CREATION
*/

-- DROP DATABASE "hotel-rent";

-- CREATE DATABASE "hotel-rent"
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     CONNECTION LIMIT = -1
--     IS_TEMPLATE = False;

CREATE TABLE "role"
(
    rolename VARCHAR(46) PRIMARY KEY NOT NULL UNIQUE
);

CREATE TABLE "account_status" (
    status VARCHAR(46) PRIMARY KEY NOT NULL UNIQUE
);

CREATE TABLE "user"
(
    id            SERIAL,
    first_name    VARCHAR(46)   NOT NULL,
    last_name     VARCHAR(46)   NOT NULL,
    nickname      VARCHAR(24)   NOT NULL,
    login         VARCHAR(46)   NOT NULL UNIQUE,
    password      VARCHAR(60)   NOT NULL,
    balance       NUMERIC(8, 2) DEFAULT 0.0,
    age           INT DEFAULT 0,
    phone_number  VARCHAR(46) DEFAULT 'NOT_MENTIONED',
    PRIMARY KEY (id),
    role_rolename VARCHAR(46) REFERENCES role (rolename) ON DELETE CASCADE NOT NULL,
    "account_status" VARCHAR(46) REFERENCES account_status (status) ON DELETE CASCADE NOT NULL,
    constraint balance_check check (balance >= 0)
);

CREATE TABLE "location"
(
    id   SERIAL,
    name VARCHAR(46) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE "hotel"
(
    id          SERIAL,
    name        VARCHAR(46),
    PRIMARY KEY (id),
    location_id INT REFERENCES location (id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE "room"
(
    id            SERIAL,
    price         NUMERIC(5, 2) NOT NULL,
    brief_description varchar(128) NOT NULL,
    description   VARCHAR(256)  NOT NULL,
    img_reference VARCHAR(256)  NOT NULL,
    total_rate    NUMERIC(2,1),
    person_amount INT NOT NULL,
    PRIMARY KEY (id),
    hotel_id      INT REFERENCES hotel (id) ON DELETE CASCADE NOT NULL,
    constraint rate_limits check (total_rate >= 0 and total_rate <= 5)
--     constraint person_amount_check check (person_amount > 1 and person_amount <= 5)
);

CREATE TABLE "user_has_room"
(
    id         SERIAL,
    start_rent DATE NOT NULL,
    end_rent   DATE NOT NULL,
    PRIMARY KEY (id),
    user_id    INT REFERENCES "user" (id) ON DELETE CASCADE NOT NULL,
    room_id    INT REFERENCES room (id) ON DELETE CASCADE NOT NULL,
    constraint "start_date_check" check ("start_rent" >= CURRENT_DATE),
    constraint end_date_check check (end_rent >= start_rent)
);

CREATE TABLE "user_response"
(
    id        SERIAL,
    comment VARCHAR(256) NOT NULL,
    date_time TIMESTAMP DEFAULT NOW(),
    rate      NUMERIC(2,1)     NOT NULL,
    PRIMARY KEY (id),
    user_id   INT REFERENCES "user" (id) ON DELETE CASCADE NOT NULL,
    room_id   INT REFERENCES room (id) ON DELETE CASCADE NOT NULL,
    constraint rate_limits check (rate > 0 and rate <= 5)
);

INSERT INTO "role" (rolename) VALUES ('USER'), ('ADMIN');

INSERT INTO "account_status" VALUES ('ACTIVE'), ('NOT ACTIVATED'), ('BLOCKED');

INSERT INTO "user" (first_name, last_name, nickname, "login", "password", role_rolename, account_status)
    VALUES ('Admin', 'Superuser', 'admin', 'admin@gmail.com', '$2a$12$AB4U2oNnczM8Ht/ZaO.43.Ze2bE3ok.vZiwZshT9AuesW3rorMGSy', 'ADMIN', 'ACTIVE');