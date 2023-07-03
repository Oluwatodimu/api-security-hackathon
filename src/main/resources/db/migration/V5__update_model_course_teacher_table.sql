ALTER TABLE course_teacher
    MODIFY COLUMN course_id BINARY(16);

ALTER TABLE course_teacher
    MODIFY COLUMN teacher_id BINARY(16);

ALTER TABLE course_teacher
    MODIFY COLUMN semester_id BINARY(16);