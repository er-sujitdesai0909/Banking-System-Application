package com.SD.BankingSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String tran_id;
    private Date date;
    private double tranamount;
    private String process;
    @ManyToOne
    private Account account;
}
