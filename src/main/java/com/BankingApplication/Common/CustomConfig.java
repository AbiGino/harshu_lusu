package com.BankingApplication.Common;

import com.BankingApplication.Config.JwtInterceptor;
import com.BankingApplication.Config.RequestMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomConfig implements WebMvcConfigurer {

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Autowired
    RequestMeta requestMeta;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor);
//    }

    @Bean
    @RequestScope
    public RequestMeta getRequestMeta() {

        return requestMeta;
    }

    @Bean
    public JwtInterceptor getJwtInterceptor() {

        return new JwtInterceptor(getRequestMeta());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }

}

