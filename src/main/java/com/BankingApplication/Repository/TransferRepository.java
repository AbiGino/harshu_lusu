package com.BankingApplication.Repository;

import com.BankingApplication.Entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
//    @Query(value = "select * from employeedetails.transfer where account_number =:account_number", nativeQuery = true)
//    Transfer findByAccountNumber(Integer account_number);
}
