package com.SD.BankingSystem.Repository;

import com.SD.BankingSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<User,Integer> {
}
