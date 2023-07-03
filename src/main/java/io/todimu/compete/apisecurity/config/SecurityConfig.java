package io.todimu.compete.apisecurity.config;

import io.todimu.compete.apisecurity.security.filter.JwtValidationFilter;
import io.todimu.compete.apisecurity.security.jwt.JwtTokenProvider;
import io.todimu.compete.apisecurity.utils.AuthoritiesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .cors().configurationSource(
                        request -> {
                            CorsConfiguration configuration = new CorsConfiguration();
                            configuration.setAllowedOrigins(Collections.singletonList("*"));
                            configuration.setAllowedMethods(Collections.singletonList("*"));
                            configuration.setAllowCredentials(true);
                            configuration.setAllowedHeaders(Collections.singletonList("*"));
                            configuration.setExposedHeaders(List.of(AuthoritiesConstants.AUTHORITIES_HEADER));
                            configuration.setMaxAge(3600L);
                            return configuration;
                        })
                .and()
                .addFilterBefore(new JwtValidationFilter(jwtTokenProvider), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/actuator/health").permitAll()
                .antMatchers("/api/v1/student/register").permitAll()
                .antMatchers("/api/v1/student/activate").permitAll()
                .antMatchers("/api/v1/user/authenticate").permitAll()
                .antMatchers("/api/v1/student/retrieve/{value}").authenticated()
                .antMatchers("/api/v1/student/retrieve").authenticated()
                .antMatchers("/api/v1/student/update").authenticated()
                .antMatchers("/api/v1/teacher/register").authenticated()
                .antMatchers("/api/v1/course").authenticated()
                .antMatchers("/api/v1/course-registration").authenticated()
                .antMatchers("/api/v1/course-teacher").authenticated()
                .antMatchers("/api/v1/course/retrieve").authenticated()
                .antMatchers("/api/v1/course-registration/retrieve").authenticated()
                .antMatchers("/api/v1/grade/retrieve").authenticated()
                .antMatchers("/api/v1/grade/update").authenticated()
                .antMatchers("/api/v1/grade/gpa").authenticated()
                .antMatchers("/api/v1/parent/retrieve").authenticated()
                .antMatchers("/api/v1/parent/update").authenticated()
        ;
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
