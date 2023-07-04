package io.todimu.compete.apisecurity.exception;

public class UserSuspendedException extends RuntimeException {

    public UserSuspendedException() {
        super();
    }

    public UserSuspendedException(String message) {
        super(message);
    }
}
