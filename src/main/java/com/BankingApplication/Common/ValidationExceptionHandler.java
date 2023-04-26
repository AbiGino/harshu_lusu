package com.BankingApplication.Common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
public class ValidationExceptionHandler extends RuntimeException{

    public List<Error> errors = new ArrayList<>();
    public ValidationExceptionHandler(String message, List<Error> error) {
        super(message);
        this.errors = error;
    }

}
