package io.todimu.compete.apisecurity.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.todimu.compete.apisecurity.exception.AcceptHeaderNotParsedInRequestException;
import io.todimu.compete.apisecurity.utils.AuthoritiesConstants;
import io.todimu.compete.apisecurity.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class AcceptHeaderFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String acceptHeader = request.getHeader("Accept");

        try {

            if (acceptHeader == null || !acceptHeader.equalsIgnoreCase(AuthoritiesConstants.ACCEPT)) {
                throw new AcceptHeaderNotParsedInRequestException("Accept header parameter should be set to application/json");
            }
            filterChain.doFilter(request, response);

        } catch (AcceptHeaderNotParsedInRequestException exception) {
            System.err.println(exception.getMessage());
            BaseResponse baseResponse = new BaseResponse(null, exception.getMessage(), true);
            response.setContentType(AuthoritiesConstants.ACCEPT);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
        }
    }
}
