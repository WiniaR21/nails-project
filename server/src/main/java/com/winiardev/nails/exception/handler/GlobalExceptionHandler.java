package com.winiardev.nails.exception.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.QueryArgumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.winiardev.nails.exception.exceptions.ErrorResponse;
import com.winiardev.nails.exception.exceptions.ResourceNotFoundException;
import com.winiardev.nails.exception.exceptions.UniqueEmailException;

/**
 * GlobalExceptionHandler is a central place for handling exceptions thrown
 * throughout the application.
 * It handles exceptions by returning custom error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        /**
         * Handles MethodArgumentNotValidException, which is thrown when validation on
         * an argument object fails.
         *
         * @param ex      the MethodArgumentNotValidException that was thrown
         * @param headers the HTTP headers
         * @param status  the HTTP status code
         * @param request the current web request
         * @return a ResponseEntity containing validation error details and a
         *         BAD_REQUEST status
         */
        @SuppressWarnings("null")
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(
                        MethodArgumentNotValidException ex,
                        HttpHeaders headers,
                        HttpStatusCode status,
                        WebRequest request) {

                Map<String, String> validationErrors = new HashMap<>();
                List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

                validationErrorList.forEach((error) -> {
                        String fieldName = ((FieldError) error).getField();
                        String validationMsg = error.getDefaultMessage();
                        validationErrors.put(fieldName, validationMsg);
                });

                return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
        }

        /**
         * Handles IllegalArgumentException thrown when an illegal argument is passed to
         * a method.
         *
         * @param exception the IllegalArgumentException that was thrown
         * @param request   the current web request
         * @return a ResponseEntity containing error details and a BAD_REQUEST status
         */
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
                        IllegalArgumentException exception,
                        WebRequest request) {

                ErrorResponse errorResponse = new ErrorResponse(
                                request.getDescription(false),
                                HttpStatus.BAD_REQUEST,
                                exception.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        /**
         * Handles UniqueEmailException thrown when an email is not unique.
         *
         * @param exception the UniqueEmailException that was thrown
         * @param request   the current web request
         * @return a ResponseEntity containing error details and a CONFLICT status
         */
        @ExceptionHandler(UniqueEmailException.class)
        public ResponseEntity<ErrorResponse> handleUniqueEmailException(
                        UniqueEmailException exception,
                        WebRequest request) {

                ErrorResponse errorResponse = new ErrorResponse(
                                request.getDescription(false),
                                HttpStatus.CONFLICT,
                                exception.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        /**
         * Handles ResourceNotFoundException thrown when a requested resource is not
         * found.
         *
         * @param exception the ResourceNotFoundException that was thrown
         * @param request   the current web request
         * @return a ResponseEntity containing error details and a NOT_FOUND status
         */
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
                        ResourceNotFoundException exception,
                        WebRequest request) {

                ErrorResponse errorResponse = new ErrorResponse(
                                request.getDescription(false),
                                HttpStatus.NOT_FOUND,
                                exception.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        /**
         * Handles UsernameNotFoundException thrown when a username is not found.
         *
         * @param exception the UsernameNotFoundException that was thrown
         * @param request   the current web request
         * @return a ResponseEntity containing error details and a NOT_FOUND status
         */
        @ExceptionHandler(UsernameNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(
                        UsernameNotFoundException exception,
                        WebRequest request) {

                ErrorResponse errorResponse = new ErrorResponse(
                                request.getDescription(false),
                                HttpStatus.NOT_FOUND,
                                exception.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        /**
         * Handles QueryArgumentException thrown for invalid query arguments.
         *
         * @param exception the QueryArgumentException that was thrown
         * @param request   the current web request
         * @return a ResponseEntity containing error details and a BAD_REQUEST status
         */
        @ExceptionHandler(QueryArgumentException.class)
        public ResponseEntity<ErrorResponse> handleQueryArgumentException(
                        QueryArgumentException exception,
                        WebRequest request) {

                ErrorResponse errorResponse = new ErrorResponse(
                                request.getDescription(false),
                                HttpStatus.BAD_REQUEST,
                                exception.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
}
