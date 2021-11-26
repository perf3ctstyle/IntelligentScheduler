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

    private static final String REQUIRED_FIELDS_MISSING = "Some of the required fields were missing in the request.";
    private static final String ILLEGAL_ARGUMENTS = "Some of the fields in the request had illegal values.";
    private static final String RESOURCE_NOT_FOUND = "The required resource hasn't been found.";
    private static final String RESOURCE_ALREADY_EXISTS = "Such a resource already exists.";

    private static final int BAD_REQUEST_CODE = HttpStatus.BAD_REQUEST.value();
    private static final int RESOURCE_NOT_FOUND_CODE = HttpStatus.NOT_FOUND.value();
    private static final int CONFLICT_CODE = HttpStatus.CONFLICT.value();

    @ExceptionHandler(RequiredFieldsMissingException.class)
    public ResponseEntity<ExceptionResponseDto> handleRequiredFieldsMissingException() {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(REQUIRED_FIELDS_MISSING, BAD_REQUEST_CODE);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDto> handleIllegalArgumentException() {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(ILLEGAL_ARGUMENTS, BAD_REQUEST_CODE);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException() {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(RESOURCE_NOT_FOUND, RESOURCE_NOT_FOUND_CODE);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceAlreadyExistsException() {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(RESOURCE_ALREADY_EXISTS, CONFLICT_CODE);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}
