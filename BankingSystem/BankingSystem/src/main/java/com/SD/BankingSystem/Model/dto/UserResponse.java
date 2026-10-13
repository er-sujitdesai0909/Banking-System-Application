package com.SD.BankingSystem.Model.dto;

import com.SD.BankingSystem.Model.Account;

import java.sql.Date;

public record UserResponse(Long id, String name, String email, Date birthdate) {
}
