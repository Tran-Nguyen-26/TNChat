package com.backend.app.exception;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.app.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
    ErrorResponse error = ErrorResponse.builder()
        .timestamp(new Date(System.currentTimeMillis()))
        .status(HttpStatus.BAD_REQUEST.value())
        .path(request.getRequestURI())
        .error("Runtime error")
        .message(e.getMessage())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException e, HttpServletRequest request) {
    ErrorResponse error = ErrorResponse.builder()
        .timestamp(new Date(System.currentTimeMillis()))
        .status(HttpStatus.NOT_FOUND.value())
        .path(request.getRequestURI())
        .error("User not found")
        .message(e.getMessage())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleUserAlreadyExistsExceptioin(UserAlreadyExistsException e, HttpServletRequest request) {
    ErrorResponse error = ErrorResponse.builder()
        .timestamp(new Date(System.currentTimeMillis()))
        .status(HttpStatus.CONFLICT.value())
        .path(request.getRequestURI())
        .error("Already exists")
        .message(e.getMessage())
        .build();
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentailsException(BadCredentialsException e,
      HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
        new Date(System.currentTimeMillis()),
        HttpStatus.UNAUTHORIZED.value(),
        request.getRequestURI(),
        "Unauthorized",
        "Incorrect email or password");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
  }
}
