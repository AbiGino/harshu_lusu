package com.BankingApplication.Common;

import lombok.*;

@Setter
@Getter
public class JWTAccessDeniedException  extends RuntimeException{
    public JWTAccessDeniedException(String message)
    {
        super(message);
    }

}
