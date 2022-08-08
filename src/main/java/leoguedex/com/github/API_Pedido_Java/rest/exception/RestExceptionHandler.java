package leoguedex.com.github.API_Pedido_Java.rest.exception;

import leoguedex.com.github.API_Pedido_Java.exception.AuthorizationException;
import leoguedex.com.github.API_Pedido_Java.exception.DataIntegratyException;
import leoguedex.com.github.API_Pedido_Java.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handlerObjectNotFoundException(ObjectNotFoundException e) {
        StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegratyException.class)
    public ResponseEntity<StandardError> handlerDataIntegratyException(DataIntegratyException e, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação nos campos", System.currentTimeMillis());
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            validationError.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);

    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> handlerAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standardError);
    }

}