package com.BankingApplication.EntityTest;

import com.BankingApplication.Entity.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    @Test
    public void transactionEntity(){
        Transaction transaction=new Transaction();
        transaction.setTransaction_id(1);
        transaction.setTransaction_type("deposit");
        transaction.setAccount_number(1);
        transaction.setAmount(1000);
        transaction.setTransaction_at(1234567L);

        assertEquals(1, transaction.getTransaction_id());
        assertEquals(1, transaction.getAccount_number());
        assertEquals(1000, transaction.getAmount());
        assertEquals(1234567L, transaction.getTransaction_at());
        assertEquals("deposit",transaction.getTransaction_type());
    }
}
