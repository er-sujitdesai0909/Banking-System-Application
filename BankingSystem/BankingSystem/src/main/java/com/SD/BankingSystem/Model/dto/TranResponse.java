package com.SD.BankingSystem.Model.dto;

import java.sql.Date;

public record TranResponse(Long userId, String userEmail, String tran_Id, Date date,String process,double TransAmnt,double avlBalance) {
}
