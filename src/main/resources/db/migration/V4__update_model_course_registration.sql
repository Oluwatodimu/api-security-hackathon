ALTER TABLE course_registration
    MODIFY COLUMN course_id BINARY(16);

ALTER TABLE course_registration
    MODIFY COLUMN student_id BINARY(16);

ALTER TABLE course_grade
    MODIFY COLUMN course_registration_id BINARY(16);