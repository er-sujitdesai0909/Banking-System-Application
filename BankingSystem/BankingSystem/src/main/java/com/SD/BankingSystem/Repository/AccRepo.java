package com.SD.BankingSystem.Repository;

import com.SD.BankingSystem.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccRepo extends JpaRepository<Account,Integer> {
}
