package com.BankingApplication.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RootGetById {
    public int status;

    public String message;

    public Data data;
}
