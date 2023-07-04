package io.todimu.compete.apisecurity.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course_grade")
@EqualsAndHashCode(callSuper = true)
public class CourseGrade extends BaseEntity {

    @Column(name = "grade")
    private Float grade;

    @OneToOne
    @JoinColumn(name = "course_registration_id")
    private CourseRegistration  courseRegistration;


    @Override
    public int hashCode() {
        return Objects.hash(grade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseGrade)) return false;
        CourseGrade other = (CourseGrade) o;
        return Objects.equals(grade, other.grade) &&
                Objects.equals(courseRegistration, other.courseRegistration);
    }
}
