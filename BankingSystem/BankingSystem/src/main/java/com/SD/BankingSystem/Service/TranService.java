package com.SD.BankingSystem.Service;

import com.SD.BankingSystem.Model.Account;
import com.SD.BankingSystem.Model.Transaction;
import com.SD.BankingSystem.Model.User;
import com.SD.BankingSystem.Model.dto.TranAllResponse;
import com.SD.BankingSystem.Model.dto.TranRequest;
import com.SD.BankingSystem.Model.dto.TranResponse;
import com.SD.BankingSystem.Repository.AccRepo;
import com.SD.BankingSystem.Repository.Repo;
import com.SD.BankingSystem.Repository.TranRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TranService {
    @Autowired
    private TranRepo tranRepo;
    @Autowired
    private Repo repo;
    @Autowired
    private AccRepo accRepo;
    public TranResponse deposit(TranRequest tranRequest) {

        User user = repo.findById(Math.toIntExact(tranRequest.id())).orElseThrow(()->new RuntimeException("User Not Found"));
        if(!user.getPassword().equals(tranRequest.password()))return null;
        Account account = user.getAccount();
        account.setBalance(account.getBalance()+tranRequest.amount());
        Account savedAcc = accRepo.save(account);
        user.setAccount(savedAcc);
        repo.save(user);
        Transaction transaction = new Transaction();
        transaction.setDate(Date.valueOf(LocalDate.now()));
        transaction.setAccount(savedAcc);
        transaction.setTran_id("TRAN"+UUID.randomUUID().toString().toUpperCase().substring(0,8));
        transaction.setTranamount(tranRequest.amount());
        transaction.setProcess("Deposit");
        tranRepo.save(transaction);
        TranResponse tranResponse = new TranResponse(user.getId(), user.getEmail(),transaction.getTran_id(),transaction.getDate(),"Deposit Money",transaction.getTranamount(),savedAcc.getBalance());
        return tranResponse;
    }

    public TranResponse withdraw(TranRequest tranRequest) {
        User user = repo.findById(Math.toIntExact(tranRequest.id())).orElseThrow(()->new RuntimeException("User Not Found"));
        if(!user.getPassword().equals(tranRequest.password()))return null;
        Account account = user.getAccount();
        if(account.getBalance()<tranRequest.amount()){
            return null;
        }
        account.setBalance(account.getBalance()-tranRequest.amount());
        Account savedAcc = accRepo.save(account);
        user.setAccount(savedAcc);
        repo.save(user);
        Transaction transaction = new Transaction();
        transaction.setDate(Date.valueOf(LocalDate.now()));
        transaction.setAccount(savedAcc);
        transaction.setTran_id("TRAN"+UUID.randomUUID().toString().toUpperCase().substring(0,8));
        transaction.setTranamount(tranRequest.amount());
        transaction.setProcess("Withdraw");
        tranRepo.save(transaction);
        TranResponse tranResponse = new TranResponse(user.getId(),user.getEmail(),transaction.getTran_id(),transaction.getDate(),"WithDraw Money",transaction.getTranamount(),savedAcc.getBalance());

        return tranResponse;
    }

    public List<TranAllResponse> getAll(int userId, String pass) {
        User user = repo.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
        if(!user.getPassword().equals(pass))return null;
        int id = Math.toIntExact(user.getAccount().getId());
        List<Transaction> list = new ArrayList<>();
        list = tranRepo.findAllByAccId(id);
        List<TranAllResponse>tranAllResponses = new ArrayList<>();
        for(Transaction t : list){
            TranAllResponse tranAllResponse = new TranAllResponse(t.getDate(),t.getProcess(),t.getTran_id(),t.getTranamount());
            tranAllResponses.add(tranAllResponse);
        }
        return tranAllResponses;
    }
}
