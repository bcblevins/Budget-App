package com.blevins.BudgetApp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Income {
    private int id;
    private String name;
    private BigDecimal amount;
    private boolean isMonthly;
    private int timesPerMonth;
    private int weeksPerPeriod;
}
