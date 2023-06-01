//package com.BankingApplication.ServiceTest;

//import com.BankingApplication.Common.APIResponse;
//import com.BankingApplication.DTO.LoginRequestDTO;
//import com.BankingApplication.Entity.Customer;
//import com.BankingApplication.Repository.CustomerRepository;
//import com.BankingApplication.Service.LoginService;
//import com.BankingApplication.Utils.JwtUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//import java.util.HashMap;
//import java.util.Map;

//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ LoginService.class })
//public class LoginServiceTest {

//    @InjectMocks
//    LoginService loginService;

//    @Mock
//    LoginRequestDTO loginRequestDTO;

//    @Mock
//    CustomerRepository customerRepository;
//    @Mock
//    Customer customer;
//    @Mock
//    BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Mock
//    APIResponse apiResponseResponseEntity;
//    @Mock
//    JwtUtils jwtUtils;
//    @Autowired
//    MockMvc mockMvc;

//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(new LoginService()).build();
//    }
//
//    @BeforeEach
//    public void start() {
//        apiResponseResponseEntity.setStatus(200);
//        apiResponseResponseEntity.setMessage("Logged in Successfully");
//        apiResponseResponseEntity.setError(null);
//
//    }
//
//    @Test
//    @Order(1)
//    public void login() throws Exception {
//
//        String name = "abi";
//        String password = "Abi@20";
//        String encodedPassword = "jiwYeQUKwe";
//        Customer customer = new Customer();
//        customer.setName(name);
//        customer.setPassword(encodedPassword);
//        when(customerRepository.findByNameAndPassword(name)).thenReturn(customer);
//        when(bCryptPasswordEncoder.matches(password, encodedPassword)).thenReturn(true);
//        String token = "token";
//        when(jwtUtils.generateJwt(customer)).thenReturn(token);
//        Map<String, Object> expectedData = new HashMap<>();
//        expectedData.put("Token", token);
//        APIResponse expectedResponse = new APIResponse();
//        expectedResponse.setStatus(200);
//        expectedResponse.setData(expectedData);
//        expectedResponse.setMessage("Logged in Successfully");
//        Assertions.assertEquals(HttpStatus.OK, loginService.login(loginRequestDTO).getStatusCode());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(String.valueOf(loginRequestDTO)))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @Order(2)
//    public void testLoginInvalidCredentials() throws Exception {
//        String name = "abi123";
//        String password = "Abi=+";
//        String encodedPassword = "AbiGinoBerly123439";
//        Customer customer = new Customer();
//        customer.setName(name);
//        customer.setPassword(encodedPassword);
//        when(customerRepository.findByNameAndPassword(name)).thenReturn(null);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = objectMapper.writeValueAsString(new LoginRequestDTO(name, password));
//        APIResponse expectedResponse = new APIResponse();
//        expectedResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//        expectedResponse.setData(null);
//        expectedResponse.setMessage("Invalid username or password");
//
//        // Act and Assert
//        mockMvc.perform(post("/api/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.status").value(expectedResponse.getStatus()))
//                .andExpect(jsonPath("$.message").value(expectedResponse.getMessage()));
//    }
//
//    @Test
//    @Order(2)
//    public void testInvalidCredentials() {
//        String name = "abi";
//        String password = "Abi@20";
//        String encodedPassword = "jdKseKdWqIeXwoOeu2oKeu";
//        Customer customer = new Customer();
//        customer.setName(name);
//        customer.setPassword(password);
//        when(customerRepository.findByNameAndPassword(name)).thenReturn(customer);
//        when(bCryptPasswordEncoder.matches(password, encodedPassword)).thenReturn(true);
//        String token = "wKwOcNqEiIRU3CWoJRjnQneQ";
//        when(jwtUtils.generateJwt(customer)).thenReturn(token);
//        Map<String, Object> expectedData = new HashMap<>();
//        expectedData.put("Token", token);
//        APIResponse data = new APIResponse();
//        data.setStatus(200);
//        data.setData(expectedData);
//        data.setMessage("Logged in Successfully");
//        Assertions.assertEquals(HttpStatus.OK, loginService.login(loginRequestDTO).getStatusCode());
//
//    }
//
//}
