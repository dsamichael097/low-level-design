package com.example.lowleveldesign.splitwise.domain.expense_type;

import com.example.lowleveldesign.splitwise.domain.User;
import com.example.lowleveldesign.splitwise.domain.split_type.Split;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public abstract class Expense {
    private User paidByUser;
    private List<Split> splits;
    private double totalAmount;

    public Expense(User paidByUser, List<Split> splits, double totalAmount) {
        this.paidByUser = paidByUser;
        this.splits = splits;
        this.totalAmount = totalAmount;
    }

    public User getPaidByUser() {
        return paidByUser;
    }

    public void setPaidByUser(User paidByUser) {
        this.paidByUser = paidByUser;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public abstract boolean validateSplits();
}
