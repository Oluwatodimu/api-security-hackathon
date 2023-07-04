package io.todimu.compete.apisecurity;

import com.treblle.spring.annotation.EnableTreblle;
import io.todimu.compete.apisecurity.enums.UserStatus;
import io.todimu.compete.apisecurity.model.Authority;
import io.todimu.compete.apisecurity.model.User;
import io.todimu.compete.apisecurity.repository.AuthorityRepository;
import io.todimu.compete.apisecurity.repository.UserRepository;
import io.todimu.compete.apisecurity.utils.AuthoritiesConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@EnableTreblle
@SpringBootApplication
public class ApiSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSecurityApplication.class, args);
    }

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                                                               AuthorityRepository authorityRepository) {

        return (args -> {
            Optional<User> adminUserOptional = userRepository.findByEmailIgnoreCase("admin@gmail.com");
            if (adminUserOptional.isEmpty()) {
                User adminUser = User.builder()
                        .userId(UUID.randomUUID())
                        .password(passwordEncoder.encode("password123$"))
                        .firstName("Sosuke")
                        .lastName("Aizen")
                        .email("admin@gmail.com")
                        .phoneNumber("2348102032276")
                        .activated(true)
                        .userStatus(UserStatus.ACTIVE)
                        .build();

                adminUser = userRepository.save(adminUser);

                User finalAdminUser = adminUser;
                Set<Authority> authorities = new HashSet<>();
                authorities.add(Authority.builder().user(finalAdminUser).role(AuthoritiesConstants.ADMIN).build());
                authorities.forEach(authority -> authority.setUser(finalAdminUser));
                authorityRepository.saveAll(authorities);
            }
        });
    }
}
