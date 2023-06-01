package com.BankingApplication.ServiceTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Entity.Withdraw;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.TransactionRepository;
import com.BankingApplication.Repository.WithdrawRepository;
import com.BankingApplication.Service.WithdrawService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WithdrawServiceTest {

    @InjectMocks
    WithdrawService withdrawService;

    @Mock
    WithdrawRepository withdrawRepository;
    @Mock
    Transaction transaction;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    Account account;
    @Mock
    Withdraw withdraw;
    @Mock
    APIResponse apiResponseResponseEntity;

    @BeforeEach
    public void start() {
        withdraw.setAccount_number(1);
        withdraw.setWithdraw_amount(100);
        withdraw.setBalance(500);

        apiResponseResponseEntity.setStatus(200);
        apiResponseResponseEntity.setMessage("Data found");
        apiResponseResponseEntity.setData(withdraw);
        apiResponseResponseEntity.setError(null);

    }

    @Test
    @Order(1)
    public void withdraw() {
        Withdraw withdraw = new Withdraw();
        withdraw.setBalance(500);
        withdraw.setAccount_number(1);
        withdraw.setWithdraw_amount(100);
        Transaction transaction = new Transaction();
        account.setBalance(100);
        when(accountRepository.findByAccountNumber(1)).thenReturn(account);
        when(withdrawRepository.save(withdraw)).thenReturn(withdraw);
        transaction.setTransaction_type("withdraw");
        transaction.setAccount_number(1);
        transaction.setAmount(100);
        transaction.setTransaction_at(123456L);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        Assertions.assertEquals(HttpStatus.OK, withdrawService.withdraw(1, 100).getStatusCode());

        Withdraw duplicate = new Withdraw();
        duplicate.setWithdraw_amount(0);
        duplicate.setAccount_number(0);
        duplicate.setBalance(500);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, withdrawService.withdraw(0, 0).getStatusCode());
    }

    @Test
    @Order(2)
    public void WithdrawInsufficientBalanceTest() {

        Account account1 = new Account();
        account1.setAccount_number(1);
        account1.setBalance(0);
        when(accountRepository.findByAccountNumber(1)).thenReturn(account1);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, withdrawService.withdraw(1, 0).getStatusCode());
    }

    @Test
    @Order(3)
    public void WithdrawInsufficientBalanceFromAccount() {

        Account account1 = new Account();
        account1.setAccount_number(1);
        account1.setBalance(100);
        when(accountRepository.findByAccountNumber(1)).thenReturn(account1);
        Withdraw withdraw = new Withdraw();
        withdraw.setAccount_number(0);
        withdraw.setBalance(500);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, withdrawService.withdraw(0, 500).getStatusCode());
    }
}

