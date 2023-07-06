package io.todimu.compete.apisecurity.security.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllowedHeadersInterceptor implements HandlerInterceptor {

    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String PATCH = "PATCH";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String allowedMethods = determineAllowedMethods(request.getRequestURI());
        response.setHeader("Allow", allowedMethods);
        return true;
    }


    private String determineAllowedMethods(String requestURI) {

        String method;

        switch (requestURI) {
            case "/api/v1/actuator/health":
            case "/api/v1/student/retrieve/emailOrMatricNumber":
            case "/api/v1/student/retrieve":
            case "/api/v1/course/retrieve":
            case "/api/v1/course-registration/retrieve":
            case "/api/v1/grade/retrieve":
            case "/api/v1/grade/gpa":
            case "/api/v1/parent/retrieve":
            case "/api/v1/user/authenticate":
                method = GET;
                break;

            case "/api/v1/student/register":
            case "/api/v1/teacher/register":
            case "/api/v1/course":
            case "/api/v1/course-registration":
            case "/api/v1/parent/create":
            case "/api/v1/course-teacher":
                method = POST;
                break;

            case "/api/v1/student/activate":
            case "/api/v1/student/update":
            case "/api/v1/grade/update":
                method = PATCH;
                break;
            default:
                method = "UNKNOWN";
        }
        return method;
    }
}
