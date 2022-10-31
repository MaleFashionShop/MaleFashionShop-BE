package com.malefashionshop.exceptions.handlers;

import com.malefashionshop.dto.response.ErrorResponse;
import com.malefashionshop.dto.response.UploadImageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxSizeException(MaxUploadSizeExceededException exc) {

        ErrorResponse error = new ErrorResponse("417", exc.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.EXPECTATION_FAILED);
    }
}
