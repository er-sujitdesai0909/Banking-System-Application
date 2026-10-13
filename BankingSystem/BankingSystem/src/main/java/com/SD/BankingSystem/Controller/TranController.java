package com.SD.BankingSystem.Controller;

import com.SD.BankingSystem.Model.dto.TranAllResponse;
import com.SD.BankingSystem.Model.dto.TranRequest;
import com.SD.BankingSystem.Model.dto.TranResponse;
import com.SD.BankingSystem.Service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TranController {
        @Autowired
        private TranService tranService;
        @PostMapping("tran/deposit")
        public ResponseEntity<?> deposit(@RequestBody TranRequest tranRequest){
            TranResponse tranResponse = tranService.deposit(tranRequest);
            if(tranResponse==null)return new ResponseEntity<>("Incorrect PassWord",HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(tranResponse, HttpStatus.OK);
        }
        @PostMapping("tran/withDraw")
    public ResponseEntity<?> withdraw(@RequestBody TranRequest tranRequest){
            TranResponse tranResponse = tranService.withdraw(tranRequest);
            if(tranResponse==null)return new ResponseEntity<>("Incorrect Pass or InSufficient Balance",HttpStatus.EARLY_HINTS);
            return new ResponseEntity<>(tranResponse,HttpStatus.ACCEPTED);
        }
        @GetMapping("/tran/all/{id}/{pass}")
    public ResponseEntity<?> getAll(@PathVariable("id") int userId,@PathVariable("pass") String pass){
            List<TranAllResponse> responses = tranService.getAll(userId,pass);
            return new ResponseEntity<>(responses,HttpStatus.ACCEPTED);
        }
}
