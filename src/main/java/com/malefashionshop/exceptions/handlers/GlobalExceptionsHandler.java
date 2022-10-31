package com.malefashionshop.exceptions.handlers;

import com.malefashionshop.dto.response.ErrorResponse;
import com.malefashionshop.exceptions.DataConstrainConflictException;
import com.malefashionshop.exceptions.NotFoundException;
import com.malefashionshop.exceptions.ResourceNotFoundException;
//import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleResourceNotFoundException(RuntimeException exception,
                                                                         WebRequest request){
        ErrorResponse error = new ErrorResponse("404", exception.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

//    @Override
    @ExceptionHandler({DataConstrainConflictException.class})
    protected ResponseEntity<ErrorResponse> handleDataConstrainConflictException(RuntimeException exception,
                                                                            WebRequest request){
        ErrorResponse error = new ErrorResponse("409", exception.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException exception,
                                                                    WebRequest request) {
        ErrorResponse error = new ErrorResponse("404", exception.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }






}
