CREATE TABLE `course` (
    `id` BINARY(16) NOT NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `name` VARCHAR(64) NOT NULL UNIQUE,
    `code` VARCHAR(64) NOT NULL UNIQUE,
    `units` INT NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `student` (
    `id` BINARY(16) NOT  NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `first_name` VARCHAR(64) NOT NULL,
    `last_name` VARCHAR(64) NOT NULL,
    `email` VARCHAR(64) NOT NULL UNIQUE,
    `phone_number` VARCHAR(64) NOT NULL UNIQUE,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `teacher` (
    `id` BINARY(16) NOT  NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `first_name` VARCHAR(64) NOT NULL,
    `last_name` VARCHAR(64) NOT NULL,
    `email` VARCHAR(64) NOT NULL UNIQUE,
    `phone_number` VARCHAR(64) NOT NULL UNIQUE,
    `teacher_status` VARCHAR(64) NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `course_grade` (
    `id` BINARY(16) NOT NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `course_registration_id` VARCHAR(64) NOT NULL UNIQUE,
    `grade` DECIMAL(3,2) NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `semester` (
    `id` BINARY(16) NOT NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `start_date` DATETIME,
    `end_date` DATETIME,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `course_teacher` (
    `id` BINARY(16) NOT NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `course_id` VARCHAR(64),
    `teacher_id` VARCHAR(64),
    `semester_id` VARCHAR(64),

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `course_registration` (
    `id` BINARY(16) NOT NULL UNIQUE,
    `created_by` VARCHAR(255),
    `creation_date` DATETIME,
    `last_modified_by` VARCHAR(255),
    `last_modified_date` DATETIME,
    `student_id` VARCHAR(64),
    `course_id` VARCHAR(64),
    `status` VARCHAR(64),
    `semester_id` VARCHAR(64),

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
