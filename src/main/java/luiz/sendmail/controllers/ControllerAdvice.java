package luiz.sendmail.controllers;

import luiz.sendmail.exceptions.EmailNotSent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(EmailNotSent.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle404(EmailNotSent e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleJsonValueInvalid(MethodArgumentNotValidException ex) {
        var fields = ex.getFieldErrors().stream()
                .map(error -> String.format("'%s %s'", error.getField(), error.getDefaultMessage()))
                .reduce("", (c, e) -> c + e + ", \n");
        return String.format("{'error': 'invalid request pattern',%n'message': %s}", fields);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class) // JSON value invalid
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String missingAttribute(HttpMessageNotReadableException e) {
        return String.format("{'error': 'BAD_REQUEST',%n'message': %s}", e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String methodNotAllowed() {
        return "{'error': 'Method_Not_Allowed',\n'message': 'Endpoint with this method is invalid'}";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class) // type url param
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        if (e != null) {
            var types = e.getRequiredType().getName().split("\\.");
            return String.format("{'error': 'BAD_REQUEST',%n'message': '%s should be of type %s'}", e.getName(), types[types.length - 1]);
        }
        return "{'error': 'Bad BAD_REQUEST'}";
    }
}