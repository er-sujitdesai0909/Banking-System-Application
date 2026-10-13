package com.SD.BankingSystem.Model.dto;

import java.sql.Date;

public record TranAllResponse(Date date,String process,String tran_id,double tran_amount) {
}
