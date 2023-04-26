package com.BankingApplication.Repository;

import com.BankingApplication.Entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {

//    @Query(value = "select * from employeedetails.deposit where account_number =:account_number", nativeQuery = true)
//    Deposit findByAccountNumber(Integer accountNumber);
//    @Query(value = "select * from employeedetails.account where account_number =:account_number", nativeQuery = true)
//    Account findByAccountNumber(Integer account_number,Integer balance);
}
