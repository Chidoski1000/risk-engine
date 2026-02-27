package com.remi.risk.risk_engine.persistence;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RiskSignals {
    private boolean geoMismatch;
    private boolean highRiskCountry;
    private boolean nightTransaction;

    private double amountDeviationRatio;
    private int transactionsLastHour;
}
