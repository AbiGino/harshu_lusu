package com.BankingApplication.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Data {

    public int account_number;

    public String name;
    public String email;

    public String password;
    public String phone_number;
}
