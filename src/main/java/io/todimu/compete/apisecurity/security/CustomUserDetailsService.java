package io.todimu.compete.apisecurity.security;

import io.todimu.compete.apisecurity.enums.UserStatus;
import io.todimu.compete.apisecurity.exception.UserNotActivatedException;
import io.todimu.compete.apisecurity.exception.UserSuspendedException;
import io.todimu.compete.apisecurity.model.User;
import io.todimu.compete.apisecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(username.toLowerCase(Locale.ENGLISH)) // username is email in this case
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("email not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        if (!user.isActivated()) {
            throw new UserNotActivatedException("User not activated");
        }

        if (user.getUserStatus().equals(UserStatus.SUSPENDED)) {
            throw new UserSuspendedException("user has been suspended");
        }

        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUserId().toString(), user.getPassword(), grantedAuthorities);
    }
}
