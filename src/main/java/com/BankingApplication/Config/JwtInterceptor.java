package com.BankingApplication.Config;

import com.BankingApplication.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RequestMeta requestMeta;

    public JwtInterceptor(RequestMeta requestMeta) {

        this.requestMeta=requestMeta;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String token=request.getHeader("Authorization");
        String JwtToken = null;
        if(token!=null && token.startsWith("Bearer")){
            JwtToken=token.substring(7,token.length());
        }
        if(!((request.getRequestURI().contains("/api/accounts/login")) || (request.getRequestURI().contains("/api/accounts/signup")))){
            Claims claims=jwtUtils.verify(JwtToken);
            System.out.println("claims : "+claims);
            if(claims.get("name") != null){
                requestMeta.setName(claims.get("name").toString());
            }
            if (claims.get("phone_number") != null) {
                requestMeta.setPhone_number(claims.get("phone_number").toString());
            }
            if (claims.get("account_type") != null) {
                requestMeta.setAccount_type(claims.get("account_type").toString());
            }

        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
