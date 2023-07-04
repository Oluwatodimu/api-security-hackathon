package io.todimu.compete.apisecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.todimu.compete.apisecurity.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString(exclude = "students")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parent")
public class Parent extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "phone_number", updatable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "student_parent",
            joinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
    )
    private Set<Student> students = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(
                firstName,
                lastName,
                email,
                phoneNumber,
                gender
        );
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return Objects.equals(firstName, parent.firstName) &&
                Objects.equals(lastName, parent.lastName) &&
                Objects.equals(email, parent.email) &&
                Objects.equals(phoneNumber, parent.phoneNumber) &&
                Objects.equals(gender, parent.gender);
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
