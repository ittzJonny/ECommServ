CREATE TABLE category
(
    id            BIGINT NOT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    state         SMALLINT NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jc_instructor
(
    user_id BINARY(16)   NOT NULL,
    company VARCHAR(255) NULL,
    CONSTRAINT pk_jc_instructor PRIMARY KEY (user_id)
);

CREATE TABLE jc_mentor
(
    user_id BINARY(16) NOT NULL,
    rating DOUBLE NULL,
    CONSTRAINT pk_jc_mentor PRIMARY KEY (user_id)
);

CREATE TABLE jc_ta
(
    user_id       BINARY(16) NOT NULL,
    help_requests INT NOT NULL,
    CONSTRAINT pk_jc_ta PRIMARY KEY (user_id)
);

CREATE TABLE jc_user
(
    id    BINARY(16)   NOT NULL,
    email VARCHAR(255) NULL,
    CONSTRAINT pk_jc_user PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BIGINT NOT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    state         SMALLINT NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    price DOUBLE NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE tpc_instructor
(
    id      BINARY(16)   NOT NULL,
    email   VARCHAR(255) NULL,
    company VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_instructor PRIMARY KEY (id)
);

CREATE TABLE tpc_mentor
(
    id    BINARY(16)   NOT NULL,
    email VARCHAR(255) NULL,
    rating DOUBLE NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE tpc_ta
(
    id            BINARY(16)   NOT NULL,
    email         VARCHAR(255) NULL,
    help_requests INT NOT NULL,
    CONSTRAINT pk_tpc_ta PRIMARY KEY (id)
);

CREATE TABLE tpc_user
(
    id    BINARY(16)   NOT NULL,
    email VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_user PRIMARY KEY (id)
);

CREATE TABLE user
(
    id             BINARY(16)   NOT NULL,
    uset_type_disc INT NULL,
    email          VARCHAR(255) NULL,
    help_requests  INT NOT NULL,
    company        VARCHAR(255) NULL,
    rating DOUBLE NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE jc_instructor
    ADD CONSTRAINT FK_JC_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jc_user (id);

ALTER TABLE jc_mentor
    ADD CONSTRAINT FK_JC_MENTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jc_user (id);

ALTER TABLE jc_ta
    ADD CONSTRAINT FK_JC_TA_ON_USER FOREIGN KEY (user_id) REFERENCES jc_user (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);