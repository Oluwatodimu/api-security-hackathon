package io.todimu.compete.apisecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.todimu.compete.apisecurity.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@ToString(exclude = "authorities")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "user_id", updatable = false)
    private UUID userId;

    @JsonIgnore
    @Column(name = "password_hash")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", updatable = false)

    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\d{13}$")
    private String phoneNumber;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "activated")
    private boolean activated = false;

    @Column(name = "password_reset_date")
    private Instant passwordResetDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(
                userId,
                password,
                firstName,
                lastName,
                email,
                phoneNumber,
                imageUrl,
                activated,
                passwordResetDate,
                userStatus
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(userId, other.userId) &&
                Objects.equals(password, other.password) &&
                Objects.equals(firstName, other.firstName) &&
                Objects.equals(lastName, other.lastName) &&
                Objects.equals(email, other.email) &&
                Objects.equals(phoneNumber, other.phoneNumber) &&
                Objects.equals(imageUrl, other.imageUrl) &&
                Objects.equals(activated, other.activated) &&
                Objects.equals(passwordResetDate, other.passwordResetDate) &&
                Objects.equals(userStatus, other.userStatus);
    }
}
