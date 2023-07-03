package io.todimu.compete.apisecurity.exception;

public class CourseAlreadyRegisteredException extends RuntimeException {

    public CourseAlreadyRegisteredException(String message) {
        super(message);
    }
}
