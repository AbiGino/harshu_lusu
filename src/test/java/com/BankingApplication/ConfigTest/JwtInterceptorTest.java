//package com.BankingApplication.ConfigTest;
//
//import com.BankingApplication.Config.JwtInterceptor;
//import com.BankingApplication.Config.RequestMeta;
//import com.BankingApplication.Utils.JwtUtils;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.web.HttpRequestHandler;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
////@ExtendWith(MockitoExtension.class)
////@MockitoSettings(strictness = Strictness.LENIENT)
////@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class JwtInterceptorTest {
//
//    @InjectMocks
//    JwtInterceptor jwtInterceptor;
//
//    @Mock
//    JwtUtils jwtUtils;
//
//    @Mock
//    RequestMeta requestMeta;
//
//    @Mock
//    HttpServletRequest request;
//
//    @Mock
//    HttpServletResponse response;
//
//    @Mock
//    HttpRequestHandler handler;
//
//    @Mock
//    Claims claims;
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        jwtInterceptor = new JwtInterceptor(requestMeta);
//        ReflectionTestUtils.setField(jwtInterceptor, "jwtUtils", jwtUtils);
//    }
//
//
//
//    @Test
//    public void testPreHandle() throws Exception {
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer Token");
//        when(request.getRequestURI()).thenReturn("/api/accounts/user");
//        Claims claims = Jwts.claims();
//        claims.put("name","abi");
//        claims.put("phone_number","9363464626");
//        claims.put("account_type","savings");
//
//        when(jwtUtils.verify("jaEyHdSqEOlFgiW")).thenReturn(claims);
//        boolean result = jwtInterceptor.preHandle(request, response, handler);
//
//        assertTrue(result);
//        verify(requestMeta).setName("abi");
//        verify(requestMeta).setPhone_number("9363464626");
//        verify(requestMeta).setAccount_type("savings");
//
//    }
//
//}
