package com.BankingApplication.Repository;

import com.BankingApplication.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    @Query(value = "select * from employeedetails.transaction where account_number =:account_number", nativeQuery = true)
    List<Transaction> findByAccountNumber(Integer account_number);
}
