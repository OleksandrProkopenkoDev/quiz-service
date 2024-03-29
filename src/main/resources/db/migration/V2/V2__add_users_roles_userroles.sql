CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT generated by default as identity PRIMARY KEY,
    keycloak_id VARCHAR(255) NOT NULL UNIQUE,
    email       VARCHAR(255),
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    is_deleted  BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS user_details
(
    id                        BIGINT generated by default as identity PRIMARY KEY,
    user_id                   BIGINT UNIQUE,
    verification_state        VARCHAR(255) NOT NULL,
    first_name                VARCHAR(255),
    last_name                 VARCHAR(255),
    father_name               VARCHAR(255),
    display_name              VARCHAR(255),
    phone_number              VARCHAR(255),
    document_images_url       VARCHAR(255),
    tax_identification_number VARCHAR(255),
    linkedin_profile          VARCHAR(255),
    facebook_profile          VARCHAR(255),
    is_deleted                BOOLEAN DEFAULT false,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Role table creation
CREATE TABLE IF NOT EXISTS roles
(
    id            BIGINT generated by default as identity PRIMARY KEY,
    creation_time TIMESTAMP    NOT NULL,
    keycloak_id   VARCHAR(255) UNIQUE,
    name          VARCHAR(255) NOT NULL UNIQUE,
    is_deleted    BOOLEAN DEFAULT false
);

-- Table to map ManyToMany relationship between User and Role
CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
    role_id BIGINT REFERENCES roles (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);
