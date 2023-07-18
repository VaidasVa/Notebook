package pro.vaidas.notebookclient.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalServerError(Exception ex) {
        String errorMessage = "An error occurred.";
        HttpStatus status = HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(errorMessage, status);
    }
}
