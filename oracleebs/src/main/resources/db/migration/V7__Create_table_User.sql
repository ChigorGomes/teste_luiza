CREATE TABLE IF NOT EXISTS tb_user (
        username varchar(255) NOT NULL,
        "password" varchar(255) NULL,
        CONSTRAINT tb_user_pkey PRIMARY KEY (username)
);