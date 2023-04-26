package com.BankingApplication.Common;

import com.BankingApplication.DTO.LoginRequestDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginRequestValidation {

   static List<Error> errors=new ArrayList<>();
    public static List<Error> validateLogin(LoginRequestDTO loginRequestDTO)
        {
//            List<Error> errors=new ArrayList<>();
//To check login username is empty/null
            if (loginRequestDTO.getName()==null || loginRequestDTO.getName().isEmpty()) {
                Error error = new Error();
                error.setError("Name cannot be empty");
                if (!errors.contains(error)) {
                    errors.add(error);
                }
            }

//To check password is empty/null
            if(loginRequestDTO.getPassword()==null || loginRequestDTO.getPassword().isEmpty())
            {
                Error error=new Error();
                error.setError("Password cannot be empty");
                if (!errors.contains(error)) {
                    errors.add(error);
                }
            }

            return errors;
        }
}


