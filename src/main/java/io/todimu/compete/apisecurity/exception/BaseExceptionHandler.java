package io.todimu.compete.apisecurity.exception;

import io.todimu.compete.apisecurity.web.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UsernameNotFoundException.class, UserNotActivatedException.class, UserSuspendedException.class,
            AuthenticationException.class, BadCredentialsException.class, })
    public BaseResponse unauthorizedExceptions(Exception exception) {
        log.error(exception.getMessage(), exception.getLocalizedMessage());
        return new BaseResponse(null, exception.getMessage(), true);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public BaseResponse validationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new BaseResponse(null, exception.getMessage(), true);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class, InvalidParameterException.class})
    public BaseResponse otherValidationExceptions(Exception exception) {
        return new BaseResponse(null, exception.getMessage(), true);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public BaseResponse handleAllExceptions(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage(), ex.getLocalizedMessage());
        return new BaseResponse(null, (ex.getMessage() != null) ? ex.getMessage() : "Oops something went wrong !!!", true);

    }

    @ResponseBody
    @ExceptionHandler({RateLimitException.class})
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public BaseResponse handleRateLimitException(RateLimitException ex) {
       return new BaseResponse(null, ex.getMessage(), true);
    }
}
