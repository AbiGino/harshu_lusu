package com.BankingApplication.EntityTest;

import com.BankingApplication.Entity.Transfer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {

    @Test
    public void TransferEntity(){
        Transfer transfer = new Transfer();
        transfer.setId(1);
        transfer.setFromAccountNumber(1);
        transfer.setToAccountNumber(2);
        transfer.setBalance(5000);

        assertEquals(1, transfer.getId());
        assertEquals(1, transfer.getFromAccountNumber());
        assertEquals(2,transfer.getToAccountNumber());
        assertEquals(5000, transfer.getBalance());
    }
}
