package com.BankingApplication.Common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidAccountException extends RuntimeException{
    public InvalidAccountException(String message) {

        super(message);
    }
}
