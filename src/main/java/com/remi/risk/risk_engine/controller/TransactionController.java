package com.remi.risk.risk_engine.controller;

import com.remi.risk.risk_engine.dto.TransactionRequest;
import com.remi.risk.risk_engine.dto.TransactionResponse;
import com.remi.risk.risk_engine.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PostMapping("/transact")
    ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionRequest request) {
       return ResponseEntity.accepted().body(transactionService.process(request));
    }
}
