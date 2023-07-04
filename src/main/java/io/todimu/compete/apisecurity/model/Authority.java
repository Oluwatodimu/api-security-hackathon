package io.todimu.compete.apisecurity.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authority")
public class Authority extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String role;

    @Override
    public int hashCode() {
        return Objects.hash(role); // Use only role for hashCode calculation
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Authority other = (Authority) obj;
        return Objects.equals(role, other.role); // Compare only role for equality
    }
}
