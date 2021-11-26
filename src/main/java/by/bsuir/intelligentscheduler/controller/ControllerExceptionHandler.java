package by.bsuir.intelligentscheduler.controller;

import by.bsuir.intelligentscheduler.dto.ExceptionResponseDto;
import by.bsuir.intelligentscheduler.exception.RequiredFieldsMissingException;
import by.bsuir.intelligentscheduler.exception.ResourceAlreadyExistsException;
import by.bsuir.intelligentscheduler.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequiredFieldsMissingException.class)
    public ResponseEntity<ExceptionResponseDto> handleRequiredFieldsMissingException() {
        String exceptionMessage = "Some of the required fields were missing in the request.";
        Integer exceptionInternalCode = HttpStatus.BAD_REQUEST.value();

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(exceptionMessage, exceptionInternalCode);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDto> handleIllegalArgumentException() {
        String exceptionMessage = "Some of the fields in the request had illegal values.";
        Integer exceptionInternalCode = HttpStatus.BAD_REQUEST.value();;

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(exceptionMessage, exceptionInternalCode);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException() {
        String exceptionMessage = "The required resource hasn't been found.";
        Integer exceptionInternalCode = HttpStatus.NOT_FOUND.value();;

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(exceptionMessage, exceptionInternalCode);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceAlreadyExistsException() {
        String exceptionMessage = "Such a resource already exists.";
        Integer exceptionInternalCode = HttpStatus.CONFLICT.value();;

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(exceptionMessage, exceptionInternalCode);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}
