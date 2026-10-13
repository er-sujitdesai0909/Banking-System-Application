package com.SD.BankingSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String password;
    private String email;
    private Date birthdate;
   @OneToOne
    private Account account;
}
