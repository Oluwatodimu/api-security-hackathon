CREATE TABLE user (
    id BINARY(16) NOT NULL UNIQUE,
    user_id BINARY(16) NOT NULL UNIQUE,
    password_hash VARCHAR(64) NOT NULL,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    image_url VARCHAR(256),
    activated BIT NOT NULL,
    password_reset_date DATETIME,
    user_status VARCHAR(15) NOT NULL,
    created_by VARCHAR(255),
    creation_date DATETIME,
    last_modified_by VARCHAR(255),
    last_modified_date DATETIME,

    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE authority (
   id BINARY(16) NOT NULL UNIQUE,
   user_id BINARY(16) NOT NULL,
   role VARCHAR(15) NOT NULL,
   created_by VARCHAR(255),
   creation_date DATETIME,
   last_modified_by VARCHAR(255),
   last_modified_date DATETIME,

   PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;