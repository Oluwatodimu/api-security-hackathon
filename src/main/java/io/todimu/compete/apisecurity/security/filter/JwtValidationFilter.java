package io.todimu.compete.apisecurity.security.filter;

import io.todimu.compete.apisecurity.security.jwt.JwtTokenProvider;
import io.todimu.compete.apisecurity.utils.AuthoritiesConstants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = resolveToken(request);
        if (StringUtils.hasText(jwtToken) && this.jwtTokenProvider.validateToken(jwtToken)) {
            Authentication authentication = this.jwtTokenProvider.getAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api/v1/user/authenticate");
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AuthoritiesConstants.AUTHORITIES_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
