package com.remi.risk.risk_engine.service;

import com.remi.risk.risk_engine.persistence.RiskSignals;
import com.remi.risk.risk_engine.persistence.Transaction;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@Component
public class RiskFeatureEngine {
    private static Set<String> HIGH_RISK_COUNTRIES = Set.of("Nigeria", "Russia", "North Korea");

    public RiskSignals compute(Transaction current, List<Transaction> userHistory) {
        boolean highRiskedCountry = HIGH_RISK_COUNTRIES.contains(current.getIpCountry());

        boolean geoMismatch = !current.getCountry().equalsIgnoreCase(current.getIpCountry());

        boolean nightTransaction = isNightTransaction(current);

        double amountDeviationRatio =
                calculateAmountDeviation(current, userHistory);

        int lastTransactionsCount = countTransactionsLastHour(current, userHistory);

        return RiskSignals.builder()
                .nightTransaction(nightTransaction)
                .amountDeviationRatio(amountDeviationRatio)
                .geoMismatch(geoMismatch)
                .highRiskCountry(highRiskedCountry)
                .transactionsLastHour(lastTransactionsCount)
                .build();
    }

    private boolean isNightTransaction(Transaction current) {
        int hour = current.getTimestamp().atZone(ZoneId.systemDefault()).getHour();

        return hour >= 0 && hour <= 5;
    }

    private double calculateAmountDeviation(Transaction current,
                                            List<Transaction> history) {

        if (history.isEmpty()) return 1.0;

        double avg = history.stream()
                .mapToDouble(Transaction::getAmount)
                .average()
                .orElse(current.getAmount());

        return current.getAmount() / avg;
    }

    private int countTransactionsLastHour(Transaction current,
                                          List<Transaction> history) {

        Instant oneHourAgo = current.getTimestamp().minusSeconds(3600);

        return (int) history.stream()
                .filter(tx -> tx.getTimestamp().isAfter(oneHourAgo))
                .count();
    }
}
