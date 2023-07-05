package io.todimu.compete.apisecurity.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.todimu.compete.apisecurity.exception.RateLimitException;
import io.todimu.compete.apisecurity.utils.AuthoritiesConstants;
import io.todimu.compete.apisecurity.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class RateLimitFilter extends GenericFilterBean {

    private final RateLimiter rateLimiter;
    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                                                                                   throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("x-rateLimit-limit", String.valueOf(rateLimiter.getRateLimiterConfig().getLimitForPeriod()));
        httpServletResponse.setHeader("x-rateLimit-remaining", String.valueOf(rateLimiter.getMetrics().getAvailablePermissions()));
        httpServletResponse.setHeader("x-rateLimit-reset", String.valueOf(rateLimiter.getRateLimiterConfig().getLimitRefreshPeriod().getSeconds()));

        try {

            if (!rateLimiter.acquirePermission()) {
                throw new RateLimitException("too many requests");
            }
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (RateLimitException exception) {
            System.err.println(exception.getMessage());
            BaseResponse baseResponse = new BaseResponse(null, exception.getMessage(), true);
            httpServletResponse.setContentType(AuthoritiesConstants.ACCEPT);
            httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(baseResponse));
        }


    }
}
