package io.todimu.compete.apisecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString(exclude = {"courseTeachers", "courseRegistrations"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course")
@EqualsAndHashCode(callSuper = true)
public class Course extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "units")
    private Integer units;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "course")
    private Set<CourseRegistration> courseRegistrations;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "course")
    private Set<CourseTeacher> courseTeachers;

    @Override
    public int hashCode() {
        return Objects.hash(name, code, units);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course other = (Course) o;
        return Objects.equals(name, other.name) &&
                Objects.equals(code, other.code) &&
                Objects.equals(units, other.units);
    }
}
