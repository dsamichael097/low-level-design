package com.example.lowleveldesign.splitwise.domain.split_type;

import com.example.lowleveldesign.splitwise.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class Split {
    private User user;
    protected double amount;

    public Split(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
