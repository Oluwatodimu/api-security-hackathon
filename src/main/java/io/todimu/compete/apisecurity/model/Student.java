package io.todimu.compete.apisecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.todimu.compete.apisecurity.enums.StudentStatus;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString(exclude = {"courseRegistrations"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_status")
    private StudentStatus studentStatus;

    @Column(name = "matric_number", updatable = false)
    private String matricNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "student", allowSetters = true)
    private Set<CourseRegistration> courseRegistrations;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private Set<Parent> parents = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phoneNumber, studentStatus, matricNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(email, student.email) &&
                Objects.equals(phoneNumber, student.phoneNumber) &&
                studentStatus == student.studentStatus &&
                Objects.equals(matricNumber, student.matricNumber);
    }
}
