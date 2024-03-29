CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL UNIQUE,
    login    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL UNIQUE
)  ENGINE = InnoDB
  DEFAULT CHARSET = utf8 ;

CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),

    UNIQUE (user_id, role_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;