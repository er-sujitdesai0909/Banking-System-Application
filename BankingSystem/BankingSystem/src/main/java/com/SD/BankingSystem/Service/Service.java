package com.SD.BankingSystem.Service;

import com.SD.BankingSystem.Model.Account;
import com.SD.BankingSystem.Model.User;
import com.SD.BankingSystem.Model.dto.AccRequest;
import com.SD.BankingSystem.Model.dto.AccResponse;
import com.SD.BankingSystem.Model.dto.UserRequest;
import com.SD.BankingSystem.Model.dto.UserResponse;
import com.SD.BankingSystem.Repository.AccRepo;
import com.SD.BankingSystem.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Repo repo;
    @Autowired
    private AccRepo accRepo;
    public UserResponse createAccount(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        Account account = new Account();
        account.setBalance(0);
        accRepo.save(account);
        user.setAccount(account);
        user.setBirthdate(userRequest.birthdate());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        repo.save(user);
        UserResponse userResponse = new UserResponse(user.getId(),user.getName(),user.getEmail(),user.getBirthdate());
        return userResponse;
    }

    public UserResponse updateAccount(int userId, UserRequest userRequest) {
        User user = repo.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
        if(!user.getPassword().equals(userRequest.password())) return null;
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setBirthdate(userRequest.birthdate());
        repo.save(user);
        UserResponse userResponse = new UserResponse(user.getId(),user.getName(),user.getEmail(),user.getBirthdate());
        return userResponse;
    }

    public AccResponse checkBalance(AccRequest accRequest) {
        User user  = repo.findById(Math.toIntExact(accRequest.id())).orElseThrow(()->new RuntimeException("User Not found"));
        if(!user.getPassword().equals(accRequest.password())){
            return null;
        }
        AccResponse accResponse = new AccResponse(user.getName(),user.getEmail(),user.getAccount().getId(),user.getAccount().getBalance());
        return accResponse;
    }
}
