package handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handlerException(Exception exception) {
        log.error(exception.getMessage(), exception);

        // 클라이언트네 내부 오류를 숨기기 위해 일반적인 오류 메시지를 반환.
        // 또한 보안상의 이유로 내부 오류 메시지를 클라이언트에 노출하는 것은 좋지 않음.
        // 어차피 로그에 오류 내용이 남아 있으므로 추적이 가능.
        return ErrorDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Unexpected error!")
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handlerException(ValidationException validationException) {
        ErrorDTO errorDTO;
        if (validationException instanceof ConstraintViolationException) {
            String violations = extracViolationsFromException((ConstraintViolationException) validationException);
            log.error(violations, validationException);
            errorDTO = ErrorDTO.builder()
                    .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(violations)
                    .build();
        } else {
            String exceptionMessage = validationException.getMessage();
            log.error(exceptionMessage, validationException);
            errorDTO = ErrorDTO.builder()
                    .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(exceptionMessage)
                    .build();
        }

        return errorDTO;
    }

    private String extracViolationsFromException(ConstraintViolationException validationException) {
        validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
        return null;
    }
}
