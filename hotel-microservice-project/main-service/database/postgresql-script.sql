DROP DATABASE "hotel-rent";

CREATE TABLE "role"
(
    rolename VARCHAR(46) PRIMARY KEY NOT NULL
);

CREATE TABLE "user"
(
    id            SERIAL,
    first_name    VARCHAR(46)   NOT NULL,
    last_name     VARCHAR(46)   NOT NULL,
    login         VARCHAR(46)   NOT NULL UNIQUE,
    password      VARCHAR(46)   NOT NULL,
    balance       NUMERIC(8, 2) NOT NULL,
    age           INT,
    phone_number  VARCHAR(46),
    PRIMARY KEY (id),
    role_rolename VARCHAR(46) REFERENCES role (rolename) ON DELETE CASCADE,
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
    location_id INT REFERENCES location (id) ON DELETE CASCADE
);

CREATE TABLE "room"
(
    id            SERIAL,
    price         NUMERIC(5, 2) NOT NULL,
    description   VARCHAR(256)  NOT NULL,
    img_reference VARCHAR(256)  NOT NULL,
    PRIMARY KEY (id),
    hotel_id      INT REFERENCES hotel (id) ON DELETE CASCADE
);

CREATE TABLE "user_has_room"
(
    id         SERIAL,
    start_rent DATE NOT NULL,
    end_rent   DATE NOT NULL,
    PRIMARY KEY (id),
    user_id    INT REFERENCES "user" (id) ON DELETE CASCADE,
    room_id    INT REFERENCES room (id) ON DELETE CASCADE
);

CREATE TABLE "user_response"
(
    id        SERIAL,
    "comment" VARCHAR(256) NOT NULL,
    date_time TIMESTAMP    NOT NULL,
    rate      SMALLINT     NOT NULL,
    PRIMARY KEY (id),
    user_id   INT REFERENCES "user" (id) ON DELETE CASCADE,
    room_id   INT REFERENCES room (id) ON DELETE CASCADE,
    constraint rate_limits check (rate > 0 and rate <= 5)
);