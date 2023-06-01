package com.BankingApplication.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
public class RootGetAll {
    public int status;
    public String message;
    public ArrayList<Data> data;
}
