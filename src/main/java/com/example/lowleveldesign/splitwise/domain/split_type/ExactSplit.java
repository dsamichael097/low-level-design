package com.example.lowleveldesign.splitwise.domain.split_type;

import com.example.lowleveldesign.splitwise.domain.User;

import java.math.BigDecimal;

public class ExactSplit extends Split{
    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }
}
