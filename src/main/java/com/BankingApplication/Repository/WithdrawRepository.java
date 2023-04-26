package com.BankingApplication.Repository;

import com.BankingApplication.Entity.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Integer> {
//    @Query(value = "select * from employeedetails.withdraw where account_number =:account_number", nativeQuery = true)
//    Withdraw findByAccountNumber(Integer accountNumber);
}
