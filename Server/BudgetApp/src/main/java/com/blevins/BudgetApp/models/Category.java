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
public class Category {
    private int id;
    private int groupId;
    private String name;
    private BigDecimal amountAssigned;
}
