package com.BankingApplication.Entity;

import jakarta.persistence.*;
import lombok.*;




@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfer")
@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer fromAccountNumber;
    private Integer toAccountNumber;
    private Integer balance;
}
