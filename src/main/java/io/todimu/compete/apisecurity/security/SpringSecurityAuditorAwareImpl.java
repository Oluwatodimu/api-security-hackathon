package io.todimu.compete.apisecurity.security;

import io.todimu.compete.apisecurity.utils.ResponseConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SpringSecurityAuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Optional<String> username = getLoggedInInUserDetails();
        return Optional.of(username.orElse(ResponseConstants.SYSTEM));
    }

    private static Optional<String> getLoggedInInUserDetails() {
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            String username = securityContext.getAuthentication().getName();
            return Optional.of(username);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
