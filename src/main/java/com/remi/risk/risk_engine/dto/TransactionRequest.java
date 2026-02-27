package com.remi.risk.risk_engine.dto;

import com.remi.risk.risk_engine.persistence.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    @NotBlank
    private String userId;

    @Positive
    private double amount;

    @NotBlank
    private String currency;

    @NotBlank
    private String merchantCategory;

    @NotBlank
    private String country;

    @NotBlank
    private String ipCountry;

    @NotNull
    private Instant timestamp;

    public Transaction convert(){
        return Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .amount(this.amount)
                .country(this.country)
                .currency(this.currency)
                .merchantCategory(this.merchantCategory)
                .userId(this.userId)
                .ipCountry(this.ipCountry)
                .timestamp(this.timestamp)
                .build();
    }

}
