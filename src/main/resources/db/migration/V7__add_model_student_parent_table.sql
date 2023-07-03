CREATE TABLE student_parent (
    parent_id BINARY(16) NOT NULL,
    student_id BINARY(16) NOT NULL,

    PRIMARY KEY (parent_id, student_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;