package io.todimu.compete.apisecurity.exception;

public class AcceptHeaderNotParsedInRequestException extends RuntimeException {

    public AcceptHeaderNotParsedInRequestException(String message) {
        super(message);
    }
}
