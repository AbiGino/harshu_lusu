package com.BankingApplication.ConfigTest;

import com.BankingApplication.Config.RequestMeta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestMetaTest {


    @Test
    public void testRequestMeta(){
        RequestMeta requestMeta = new RequestMeta();
        requestMeta.setName("abi");
        requestMeta.setAccount_type("savings");
        requestMeta.setPhone_number("9363464626");

        assertEquals("abi", requestMeta.getName());
        assertEquals("9363464626", requestMeta.getPhone_number());
        assertEquals("savings", requestMeta.getAccount_type());
    }

}