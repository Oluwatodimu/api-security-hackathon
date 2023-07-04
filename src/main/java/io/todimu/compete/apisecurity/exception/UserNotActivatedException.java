package io.todimu.compete.apisecurity.exception;

public class UserNotActivatedException extends RuntimeException {

    public UserNotActivatedException() {
        super();
    }

    public UserNotActivatedException(String message) {
        super(message);
    }
}
