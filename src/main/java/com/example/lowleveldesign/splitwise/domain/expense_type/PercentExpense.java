package com.example.lowleveldesign.splitwise.domain.expense_type;

import com.example.lowleveldesign.splitwise.domain.User;
import com.example.lowleveldesign.splitwise.domain.split_type.PercentSplit;
import com.example.lowleveldesign.splitwise.domain.split_type.Split;

import java.util.List;

public class PercentExpense extends Expense{
    public PercentExpense(User paidByUser, List<Split> splits, double totalAmount) {
        super(paidByUser, splits, totalAmount);
    }

    @Override
    public boolean validateSplits() {
        double totalPercent = 0;
        for(Split split: this.getSplits()){
            if(!(split instanceof PercentSplit))
                return false;

            totalPercent += ((PercentSplit) split).getPercent();
        }
        return totalPercent == 100.0;
    }
}
