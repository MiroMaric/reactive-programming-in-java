CREATE TABLE system_user
(
    uuid       character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name  character varying(255) NOT NULL,
    username   character varying(255) NOT NULL
);

ALTER TABLE ONLY system_user ADD CONSTRAINT system_user_pkey PRIMARY KEY (uuid);
ALTER TABLE ONLY system_user ADD CONSTRAINT uk_74y7xiqrvp39wycn0ron4xq4h UNIQUE (username);
