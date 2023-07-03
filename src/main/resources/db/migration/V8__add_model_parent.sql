CREATE TABLE `parent` (
   `id` BINARY(16) NOT NULL UNIQUE,
   `created_by` VARCHAR(255),
   `creation_date` DATETIME,
   `last_modified_by` VARCHAR(255),
   last_modified_date DATETIME,
   `first_name` VARCHAR(64) NOT NULL,
   `last_name` VARCHAR(64) NOT NULL,
   `email` VARCHAR(64) NOT NULL UNIQUE,
   `phone_number` VARCHAR(64) NOT NULL UNIQUE,
   `gender` VARCHAR(11) NOT NULL,

   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;