package com.BankingApplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;
    private Integer amount;
    private Integer account_number;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LocalDateTime transaction_at;

    private String transaction_type;

}
