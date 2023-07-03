package io.todimu.compete.apisecurity.utils;

public class MethodAuthorityConstants {

    public static final String TEACHER_ROLE = "hasRole('ROLE_TEACHER')";

    public static final String STUDENT_ROLE = "hasRole('ROLE_STUDENT')";

    public static final String ADMIN_ROLE = "hasRole('ROLE_ADMIN')";

    public static final String TEACHER_AND_ADMIN_ROLES = "hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')";

    public static final String STUDENT_AND_ADMIN_ROLES = "hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')";
}
