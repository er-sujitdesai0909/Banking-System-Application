package com.SD.BankingSystem.Model.dto;

import lombok.Builder;

import java.sql.Date;

@Builder
public record UserRequest(String name, String password, String email, Date birthdate) {
}
