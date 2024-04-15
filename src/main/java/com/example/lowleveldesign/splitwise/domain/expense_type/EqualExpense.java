package com.example.lowleveldesign.splitwise.domain.expense_type;

import com.example.lowleveldesign.splitwise.domain.User;
import com.example.lowleveldesign.splitwise.domain.split_type.EqualSplit;
import com.example.lowleveldesign.splitwise.domain.split_type.Split;

import java.math.BigDecimal;
import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(User paidByUser, List<Split> splits, double totalAmount) {
        super(paidByUser, splits, totalAmount);
    }

    @Override
    public boolean validateSplits() {
        for(Split split: this.getSplits()){
            if(!(split instanceof EqualSplit))
                return false;
        }
        return true;
    }
}
