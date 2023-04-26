package com.BankingApplication.Repository;


import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select * from employeedetails.account where account_number =:account_number", nativeQuery = true)
    Account findByAccountNumber(Integer account_number);

    @Query(value = "select * from employeedetails.customer where name =:name", nativeQuery = true)
    Customer findByNameAndPassword(String name);

    @Query(value = "select customer_id from employeedetails.customer where customer_id =:customerId", nativeQuery = true)
    Optional<Customer> findByCustomerId(Integer customerId);


//    @Query(value = "select * from employeedetails.customer where name =:name and phone_number =:phone_number and gender =:gender and email =:email", nativeQuery = true)
//    Account findByNameAndPhoneNumberAndEmailAndGender(String name, String phone_number,String gender,String email);

}
