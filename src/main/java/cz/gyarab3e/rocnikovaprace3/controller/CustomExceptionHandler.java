package cz.gyarab3e.rocnikovaprace3.controller;

import cz.gyarab3e.rocnikovaprace3.services.AccessDeniedExceptions;
import cz.gyarab3e.rocnikovaprace3.services.MoveException;
import cz.gyarab3e.rocnikovaprace3.services.NoGameException;
import cz.gyarab3e.rocnikovaprace3.services.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(MoveException.class)
        public final ResponseEntity<String> handleAllMoveExceptions(MoveException ex, WebRequest request) {
            String error = new String(ex.getMoveError().name());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<String> handleAllValidationExceptions(ValidationException ex, WebRequest request){
            String error=new String(ex.getValidationError().name());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoGameException.class)
    public final ResponseEntity<String> handleAllNotFoundExceptions(NoGameException ex, WebRequest request){
        String error=new String(ex.getNoGameError().name());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AccessDeniedExceptions.class)
    public final ResponseEntity<String> handleAllAccessDeniedExceptions(NoGameException ex, WebRequest request){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    }
