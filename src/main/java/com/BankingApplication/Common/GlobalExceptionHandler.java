package com.BankingApplication.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    APIResponse apiResponse;

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<APIResponse> handleException(InvalidAccountException e){
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(apiResponse);

    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<APIResponse> handleException(InsufficientBalanceException e){
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage(e.getMessage());
        apiResponse.setData(new ArrayList<>());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(apiResponse);

    }

    @ExceptionHandler({JWTAccessDeniedException.class})
    public ResponseEntity<?> handleAccessDeniedException(JWTAccessDeniedException e) {
        apiResponse.setStatus(401);
        apiResponse.setMessage("Invalid Token");
        apiResponse.setData(new ArrayList<>());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(apiResponse);

    }

    @ExceptionHandler(InvalidDataFoundException.class)
    public ResponseEntity<?> handleNotFoundException(InvalidDataFoundException e) {
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

}
