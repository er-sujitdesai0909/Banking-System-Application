package com.SD.BankingSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private double balance;

}
