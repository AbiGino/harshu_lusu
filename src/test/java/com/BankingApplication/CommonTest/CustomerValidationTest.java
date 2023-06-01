//package com.BankingApplication.CommonTest;
//
//import com.BankingApplication.Common.CustomerValidation;
//import com.BankingApplication.Common.Error;
//import com.BankingApplication.Entity.Customer;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//public class CustomerValidationTest {
//
//    @Test
//    @Order(1)
//    public void testValidateCustomer() {
//        Customer customer = new Customer();
//        customer.setName("John123");
//        customer.setEmail("john@example.com");
//        customer.setPhone_number("1234567890");
//        customer.setPassword("P@ssw0rd");
//
//        List<Error> errors = CustomerValidation.validateCustomer(customer);
//        Assertions.assertEquals(1, errors.size());
//    }
//
//    @Test
//    @Order(2)
//    public void testInvalidName() {
//        Customer customer = new Customer();
//        customer.setName("John123");
//
//        List<Error> errors = CustomerValidation.validateCustomer(customer);
//
////        Assertions.assertEquals(1,errors.size());
//        Assertions.assertEquals("Name is invalid", errors.get(0).getError());
//    }
//
//    @Test
//    @Order(3)
//    public void testEmptyName() {
//        Customer customer1 = new Customer();
//        customer1.setName("");
//
//        List<Error> errors = CustomerValidation.validateCustomer(customer1);
//
////        Assertions.assertEquals(1,errors.size());
//        Assertions.assertEquals("Name cannot be empty", errors.get(0).getError());
//    }
//
//    @Test
//    @Order(4)
//    public void testInvalidEmail() {
//        Customer customer2 = new Customer();
//        customer2.setEmail("john@example.com");
//        List<Error> errors = CustomerValidation.validateCustomer(customer2);
//        System.out.println("errors" +errors);
//
//        Assertions.assertEquals("Email is invalid", errors.get(0).getError());
//    }
//
//    @Test
//    @Order(5)
//    public void testEmptyEmail() {
//        Customer customer = new Customer();
//        customer.setEmail("");
//
//        List<Error> errors = CustomerValidation.validateCustomer(customer);
//
////        Assertions.assertEquals(1,errors.size());
//        System.out.println("errors" +errors);
//        Assertions.assertEquals("Email cannot be empty",errors.get(0).getError());
//    }
//
//    @Test
//    @Order(6)
//    public void testInvalidPhoneNumber() {
//        Customer customer = new Customer();
//        customer.setPhone_number("1234567890");
//
//        List<Error> errors = CustomerValidation.validateCustomer(customer);
//        Assertions.assertEquals("Phone number is invalid", errors.get(0).getError());
//    }
//
//    @Test
//    @Order(7)
//    public void testEmptyPhoneNumber() {
//        Customer customer = new Customer();
//        customer.setPhone_number(" ");
//
//        List<Error> errors = CustomerValidation.validateCustomer(customer);
//
////        Assertions.assertEquals(1, errors.size());
//        Assertions.assertEquals("Phone number cannot be empty", errors.get(0).getError());
//    }
//
//    @Test
//    @Order(8)
//    public void testInvalidPassword() {
//        Customer customer = new Customer();
//        customer.setPassword("P@ssw0rd");
//
//        List<Error> errors = CustomerValidation.validateCustomer(customer);
//
////        Assertions.assertEquals(1, errors.size());
//        Assertions.assertEquals("Password is invalid", errors.get(0).getError());
//    }
//
//    @Test
//    @Order(9)
//    public void testEmptyPassword() {
//        Customer customer = new Customer();
//        customer.setPassword("");
//
//        List<Error> errors = CustomerValidation.validateCustomer(customer);
//
//        Assertions.assertEquals(1, errors.size());
//        Assertions.assertEquals("password cannot be empty",errors.get(0).getError());
//    }
//}
