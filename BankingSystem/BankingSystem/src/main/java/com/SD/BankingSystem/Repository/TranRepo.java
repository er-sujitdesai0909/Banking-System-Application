package com.SD.BankingSystem.Repository;

import com.SD.BankingSystem.Model.Transaction;
import com.SD.BankingSystem.Model.dto.TranAllResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TranRepo extends JpaRepository<Transaction,Integer> {

@Query("select t from Transaction t where t.account.id=:id")
    List<Transaction> findAllByAccId(int id);
}
