package com.BankingApplication.CommonTest;

import com.BankingApplication.Common.APIResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class APIResponseTest {
    @Test
    public void testGettersAndSetters() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(200);
        apiResponse.setMessage("success");
        apiResponse.setError(null);
        apiResponse.setData("data");

        assertEquals(200, apiResponse.getStatus());
        assertEquals("success", apiResponse.getMessage());
        assertNull(apiResponse.getError());
        assertEquals("data", apiResponse.getData());
    }

}
