package com.BankingApplication.Repository;

import com.BankingApplication.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    //    @Query(value="select customer_id from employeedetails.account where account_number := account_number",nativeQuery = true)
//    Optional<Customer> findByCustomerId(Integer customer_id);
    @Query(value = "select * from employeedetails.account where account_number =:account_number", nativeQuery = true)
    Account findByAccountNumber(Integer account_number);

    @Query(value = "select * from employeedetails.account where customer_id =:customerId", nativeQuery = true)
    List<Account> findAccountsByCustomerId(Integer customerId);

//    @Query(value="select customer_id from employeedetails.account where account_number =:account_number",nativeQuery = true)

}
