package luiz.sendmail.controllers;

import luiz.sendmail.exceptions.EmailNotSent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(EmailNotSent.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle404(EmailNotSent e) {
        return e.getMessage();
    }
}