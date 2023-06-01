package com.BankingApplication.EntityTest;

import com.BankingApplication.Entity.Withdraw;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawTest {


    @Test
    public void withdrawTestEntity(){
        Withdraw withdraw=new Withdraw();
        withdraw.setId(1);
        withdraw.setAccount_number(1234567890);
        withdraw.setBalance(5000);
        withdraw.setWithdraw_amount(1000);

        assertEquals(1, withdraw.getId());
        assertEquals(1234567890, withdraw.getAccount_number());
        assertEquals(5000, withdraw.getBalance());
        assertEquals(1000, withdraw.getWithdraw_amount());
    }
}
