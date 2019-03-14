package bookStore.error;

import bookStore.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;

@ControllerAdvice
public class ExceptionControllerAdvice {/*

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage("Please contact your administrator");
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }*/

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        System.err.println(ex.getMessage().toString());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundExceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        System.err.println(ex.getMessage().toString());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler({IOException.class, GeneralSecurityException.class})
    public ResponseEntity<ErrorResponse> reportExceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        System.err.println(ex.getMessage().toString());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler(InvalidSaleException.class)
    public ResponseEntity<ErrorResponse> invalidSaleExceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        System.err.println(ex.getMessage().toString());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}
