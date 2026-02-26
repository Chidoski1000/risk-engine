package com.remi.risk.risk_engine.service;

import com.remi.risk.risk_engine.dto.TransactionRequest;
import com.remi.risk.risk_engine.dto.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    public TransactionResponse process(TransactionRequest request) {
        return TransactionResponse.builder()
                .transactionId(UUID.randomUUID().toString())
                .status("PROCESSING")
                .build();
    }
}
