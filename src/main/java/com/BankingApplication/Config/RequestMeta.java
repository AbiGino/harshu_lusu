package com.BankingApplication.Config;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RequestMeta {
    private String name;
    private String phone_number;
    private String account_type;
}
