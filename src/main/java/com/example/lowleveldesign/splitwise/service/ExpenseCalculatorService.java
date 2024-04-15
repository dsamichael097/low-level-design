package com.example.lowleveldesign.splitwise.service;

import com.example.lowleveldesign.splitwise.domain.User;
import com.example.lowleveldesign.splitwise.domain.expense_type.*;
import com.example.lowleveldesign.splitwise.domain.split_type.PercentSplit;
import com.example.lowleveldesign.splitwise.domain.split_type.Split;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExpenseCalculatorService {
    public Expense createExpense(User paidBy, double totalAmount, List<Split> splitList, ExpenseType expenseType){
        return switch (expenseType){
            case EQUAL -> {
                double splitAmount = ((double) Math.round(totalAmount*100/ splitList.size()))/100.0;
//                double splitAmount = totalAmount/splitList.size();
                for (Split split : splitList) {
                    split.setAmount(splitAmount);
                }
                //adjust remaining balance to first split
                //for example we want to split 10Rs with 3 persons
                //One person will pay 3.34rs and others will pay 3.33rs
                //which equals 3.34 + (3.33*2) = 3.34 + 6.66 = 10
                splitList.get(0).setAmount(splitAmount + (totalAmount-splitAmount*splitList.size()));
                yield new EqualExpense(paidBy,splitList,totalAmount);
            }
            case EXACT -> new ExactExpense(paidBy,splitList,totalAmount);
            case PERCENT -> {
                for(Split split: splitList){
                    PercentSplit percentSplit = (PercentSplit) split;
                    double splitAmount = (totalAmount * percentSplit.getPercent())/100;
                    split.setAmount(splitAmount);
                }
                yield new PercentExpense(paidBy,splitList,totalAmount);
            }
        };
    }
}
