package com.BankingApplication.EntityTest;

import com.BankingApplication.Entity.Deposit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositTest {

    @Test
    public void depositTestEntity(){
        Deposit deposit=new Deposit();
        deposit.setAccount_number(1234567890);
        deposit.setId(1);
        deposit.setBalance(5000);
        deposit.setDeposit_amount(1000);

        assertEquals(1, deposit.getId());
        assertEquals(1234567890, deposit.getAccount_number());
        assertEquals(5000, deposit.getBalance());
        assertEquals(1000, deposit.getDeposit_amount());
    }
}
