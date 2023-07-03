package io.todimu.compete.apisecurity.config;

import io.todimu.compete.apisecurity.security.interceptor.AllowedHeadersInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllowedHeadersInterceptor())
                .addPathPatterns("/actuator/health")
                .addPathPatterns("/api/v1/student/register")
                .addPathPatterns("/api/v1/student/activate")
                .addPathPatterns("/api/v1/user/authenticate")
                .addPathPatterns("/api/v1/student/retrieve/**")
                .addPathPatterns("/api/v1/student/retrieve")
                .addPathPatterns("/api/v1/student/update")
                .addPathPatterns("/api/v1/teacher/register")
                .addPathPatterns("/api/v1/course")
                .addPathPatterns("/api/v1/course-registration")
                .addPathPatterns("/api/v1/course-teacher")
                .addPathPatterns("/api/v1/course/retrieve")
                .addPathPatterns("/api/v1/course-registration/retrieve")
                .addPathPatterns("/api/v1/grade/retrieve")
                .addPathPatterns("/api/v1/grade/update")
                .addPathPatterns("/api/v1/grade/gpa")
                .addPathPatterns("/api/v1/parent/retrieve")
                .addPathPatterns("/api/v1/parent/update")
        ;
    }
}

