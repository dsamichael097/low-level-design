package com.example.lowleveldesign.splitwise.domain.expense_type;

import com.example.lowleveldesign.splitwise.domain.User;
import com.example.lowleveldesign.splitwise.domain.split_type.ExactSplit;
import com.example.lowleveldesign.splitwise.domain.split_type.Split;

import java.math.BigDecimal;
import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(User paidByUser, List<Split> splits, double totalAmount) {
        super(paidByUser, splits, totalAmount);
    }

    @Override
    public boolean validateSplits() {
        double amountSum = 0;
        for(Split split: this.getSplits()){
            if(!(split instanceof ExactSplit))
                return false;
            amountSum += split.getAmount();
        }

        return amountSum == this.getTotalAmount();
    }
}
