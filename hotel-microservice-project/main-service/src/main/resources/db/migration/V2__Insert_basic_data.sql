INSERT INTO "role" (rolename)
VALUES ('USER'),
       ('ADMIN');

INSERT INTO "account_status"
VALUES ('ACTIVE'),
       ('NOT_ACTIVATED'),
       ('BLOCKED');

INSERT INTO "user" (first_name, last_name, nickname, "login", "password", role_rolename, account_status)
VALUES ('Admin', 'Superuser', 'admin', 'admin@gmail.com',
        '$2a$12$AB4U2oNnczM8Ht/ZaO.43.Ze2bE3ok.vZiwZshT9AuesW3rorMGSy', 'ADMIN', 'ACTIVE');

INSERT INTO "location" (name)
VALUES ('Kyiv'),
       ('Lviv');

INSERT INTO "hotel" (name, location_id)
VALUES ('Spa Resort', 1),
       ('Old City Hotel', 2);