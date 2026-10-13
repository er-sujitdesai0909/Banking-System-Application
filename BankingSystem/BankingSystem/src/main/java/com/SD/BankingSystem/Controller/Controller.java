package com.SD.BankingSystem.Controller;

import com.SD.BankingSystem.Model.dto.AccRequest;
import com.SD.BankingSystem.Model.dto.AccResponse;
import com.SD.BankingSystem.Model.dto.UserRequest;
import com.SD.BankingSystem.Model.dto.UserResponse;
import com.SD.BankingSystem.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*")
public class Controller {
    @Autowired
    private Service service;
    @PostMapping("/add")
    public ResponseEntity<UserResponse> createAccount(@RequestBody UserRequest userRequest){
            UserResponse userResponse = service.createAccount(userRequest);
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("id") int userId,@RequestBody UserRequest userRequest){
        UserResponse userResponse = service.updateAccount(userId,userRequest);
        if(userResponse==null){
            return new ResponseEntity<>("InCorrect Password",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponse,HttpStatus.ACCEPTED);
    }
    @PostMapping("/check/Balance")
    public ResponseEntity<?> checkBalance(@RequestBody AccRequest accRequest){
        AccResponse accResponse = service.checkBalance(accRequest);
        if(accResponse==null)return new ResponseEntity<>("InCorrect PassWord",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(accResponse,HttpStatus.OK);
    }
}
