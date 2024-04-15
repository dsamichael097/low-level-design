package com.example.lowleveldesign.splitwise.service;

import com.example.lowleveldesign.splitwise.domain.User;
import com.example.lowleveldesign.splitwise.domain.expense_type.ExpenseType;
import com.example.lowleveldesign.splitwise.domain.split_type.EqualSplit;
import com.example.lowleveldesign.splitwise.domain.split_type.ExactSplit;
import com.example.lowleveldesign.splitwise.domain.split_type.PercentSplit;
import com.example.lowleveldesign.splitwise.domain.split_type.Split;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitService {

    public Split getSplit(User user, double splitValue, ExpenseType expenseType){
        return switch(expenseType){
            case EQUAL -> new EqualSplit(user);
            case EXACT -> new ExactSplit(user,splitValue);
            case PERCENT -> new PercentSplit(user,splitValue);
        };
    }
}
