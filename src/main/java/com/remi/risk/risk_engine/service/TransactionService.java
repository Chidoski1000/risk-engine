package com.remi.risk.risk_engine.service;

import com.remi.risk.risk_engine.dto.TransactionRequest;
import com.remi.risk.risk_engine.dto.TransactionResponse;
import com.remi.risk.risk_engine.persistence.RiskSignals;
import com.remi.risk.risk_engine.persistence.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final RiskFeatureEngine riskFeatureEngine;

    public TransactionResponse process(TransactionRequest request) {
        Transaction tx = request.convert();

        // TODO: later fetch from DynamoDB
        List<Transaction> userHistory = List.of(Transaction.builder()
                .amount(100)
                .timestamp(Instant.now().minusSeconds(1800))
                .build());

        RiskSignals signals = riskFeatureEngine.compute(tx, userHistory);

        System.out.println(signals);

        return TransactionResponse.builder()
                .transactionId(tx.getTransactionId())
                .status("PROCESSING")
                .build();
    }
}
