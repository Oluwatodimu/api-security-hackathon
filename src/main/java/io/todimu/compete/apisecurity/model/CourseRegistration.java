package io.todimu.compete.apisecurity.model;

import io.todimu.compete.apisecurity.enums.CourseRegistrationStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString(exclude = "courseGrade")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course_registration")
@EqualsAndHashCode(callSuper = true, exclude = "courseGrade")
public class CourseRegistration extends BaseEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CourseRegistrationStatus registrationStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @OneToOne(mappedBy = "courseRegistration", cascade = CascadeType.ALL)
    private CourseGrade courseGrade;

    @Override
    public int hashCode() {
        return Objects.hash(registrationStatus, student, course, semester, courseGrade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseRegistration)) return false;
        CourseRegistration other = (CourseRegistration) o;
        return Objects.equals(registrationStatus, other.registrationStatus) &&
                Objects.equals(student, other.student) &&
                Objects.equals(course, other.course) &&
                Objects.equals(semester, other.semester) &&
                Objects.equals(courseGrade, other.courseGrade);
    }
}
