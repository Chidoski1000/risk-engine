package com.remi.risk.risk_engine.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String transactionId;
    private String userId;
    private double amount;
    private String currency;
    private String merchantCategory;
    private String country;
    private String ipCountry;
    private Instant timestamp;
}
