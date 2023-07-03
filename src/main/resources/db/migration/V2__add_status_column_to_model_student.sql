ALTER TABLE student
    ADD COLUMN student_status varchar(64) NOT NULL;

ALTER TABLE student
    ADD COLUMN matric_number varchar(64) NOT NULL UNIQUE;