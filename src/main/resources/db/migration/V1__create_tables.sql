CREATE TABLE IF NOT EXISTS users
(
    id        BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(256) NOT NULL UNIQUE,
    email     VARCHAR(256) NOT NULL UNIQUE,
    password  VARCHAR(256) NOT NULL,
    created         TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated         TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    role      VARCHAR(50)  NOT NULL,
    status    VARCHAR(25) DEFAULT 'ACTIVE'
);



CREATE TABLE IF NOT EXISTS files
(
    id        BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(256) NOT NULL UNIQUE,
    location  VARCHAR(256) NOT NULL,
    created         TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated         TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    status    VARCHAR(25) DEFAULT 'ACTIVE'
);


CREATE TABLE IF NOT EXISTS events
(
    id      BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    file_id BIGINT NOT NULL,
    created         TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated         TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    status  VARCHAR(25) DEFAULT 'ACTIVE',
    FOREIGN KEY fk_events_user_id (user_id) REFERENCES users (id),
    FOREIGN KEY fk_events_file_id (file_id) REFERENCES files (id),
    UNIQUE (user_id, file_id)
);


# CREATE TABLE IF NOT EXISTS roles
# (
#     id          BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
#     name        VARCHAR(255)    NOT NULL,
#     created         TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP(),
#     updated         TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP(),
#     status      VARCHAR(25)     NOT NULL DEFAULT 'ACTIVE'
# );


# CREATE TABLE IF NOT EXISTS user_roles
# (
#     user_id BIGINT NOT NULL,
#     role_id BIGINT NOT NULL,
#     FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
#     FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
#     UNIQUE (user_id, role_id)
# );

