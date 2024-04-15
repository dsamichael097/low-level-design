package com.example.lowleveldesign.splitwise.service;

import com.example.lowleveldesign.splitwise.domain.User;
import com.example.lowleveldesign.splitwise.domain.expense_type.*;
import com.example.lowleveldesign.splitwise.domain.split_type.Split;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseCalculatorService expenseCalculatorService;

    private final Map<String, HashMap<String,Double>> expenseMap = new HashMap<>();

    public void calculateExpense(User paidBy, double totalAmount, List<Split> splitList, ExpenseType expenseType){
        Expense expense = expenseCalculatorService.createExpense(paidBy,totalAmount,splitList,expenseType);
        if(!expense.validateSplits()) {
            throw new RuntimeException("Invalid Split");
        }

        //Store and entry against user who paid
        HashMap<String,Double> map = expenseMap.getOrDefault(paidBy.getId(),new HashMap<>());
        if(map == null) map = new HashMap<>();
        for(Split split: expense.getSplits()) {
            double expenseAmt = map.getOrDefault(split.getUser().getId(), 0.0) + split.getAmount();
            map.put(split.getUser().getId(), expenseAmt);
        }
        expenseMap.put(paidBy.getId(),map);

        //Store a reverse entry with negative balance against users who are part of split
        for(Split split: expense.getSplits()) {
            map = expenseMap.getOrDefault(split.getUser().getId(),new HashMap<>());
            double expenseAmt = map.getOrDefault(paidBy.getId(), 0.0) - split.getAmount();
            map.put(paidBy.getId(), expenseAmt);
            expenseMap.put(split.getUser().getId(),map);
        }
    }

    public void showExpense(){
        List<User> users = userService.getAllUsers();
        for(User user: users){
            showExpense(user,true);
        }
    }

    public void showExpense(User user, boolean forAllUsers){
        boolean noExpense = true;
        Map<String,Double> map = expenseMap.get(user.getId());
        if(map != null) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                if((forAllUsers && entry.getValue() > 0)
                || (!forAllUsers && entry.getValue() != 0)) {
                    noExpense = false;
                    printExpense(user, userService.getUser(entry.getKey()), entry.getValue());
                }
            }
        }
        if(noExpense)
            System.out.println("No expense found for user : "+user.getName());
    }

    private void printExpense(User paidBy, User payTo, double amt){
        if(amt > 0){
            System.out.println(payTo.getName() + " owes " + paidBy.getName() + " : " + amt);
        }
        else{
            System.out.println(paidBy.getName() + " owes " + payTo.getName() + " : " + Math.abs(amt));
        }
    }
}
