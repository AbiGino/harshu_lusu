package com.BankingApplication.Common;

import com.BankingApplication.Entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomerValidation {

     static  List<Error> errors = new ArrayList<>();

    public static List<Error> validateCustomer(Customer customer) {

//To validate the user name empty/null
        if (customer.getName() == null || customer.getName().isEmpty()) {
            Error error = new Error();
            error.setError("Name cannot be empty");
            if (!errors.contains(error)) {
                errors.add(error);
            }
        } else {
            String regex = "^[A-Za-z]*$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(customer.getName());
            Boolean match = matcher.matches();
            if (!match) {
                Error error = new Error();
                error.setError("Name is invalid");
                if (!errors.contains(error)) {
                    errors.add(error);
                }
            }
        }
//To validate user email empty/null
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            Error error = new Error();
            error.setError("Email cannot be empty");
            if (!errors.contains(error)) {
                errors.add(error);
            }
        } else {
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(customer.getEmail());
            Boolean match = matcher.matches();
            if (!match) {
                Error error = new Error();
                error.setError("Email is invalid");
                if (!errors.contains(error)) {
                    errors.add(error);
                }
            }
        }


//To validate the User phone number
        if (customer.getPhone_number() == null || customer.getPhone_number().isEmpty()) {
            Error error = new Error();
            error.setError("Phone number cannot be empty");
            if (!errors.contains(error)) {
                errors.add(error);
            }
        } else {
            String regex = "^\\d{10}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(customer.getPhone_number());
            Boolean match = matcher.matches();
            if (!match) {
                Error error = new Error();
                error.setError("Phone number is invalid");
                if (!errors.contains(error)) {
                    errors.add(error);
                }
            }
        }
//To check password is empty/null
        if (customer.getPassword() == null || customer.getPassword().isEmpty()) {
            Error error = new Error();
            error.setError("Password cannot be empty");
            if (!errors.contains(error)) {
                errors.add(error);
            }
        } else {
            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,10}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(customer.getPassword());
            Boolean match = matcher.matches();
            if (!match) {
                Error error = new Error();
                error.setError("Password is invalid");
                if (!errors.contains(error)) {
                    errors.add(error);
                }
            }
        }
        return errors;

    }

}


//^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\S+$).{8,}$
//^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,10}$