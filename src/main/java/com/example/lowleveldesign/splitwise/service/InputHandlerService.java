package com.example.lowleveldesign.splitwise.service;

import com.example.lowleveldesign.splitwise.domain.expense_type.ExpenseType;
import com.example.lowleveldesign.splitwise.domain.split_type.Split;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InputHandlerService {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private SplitService splitService;

    public void handleInput(String command){
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch (commandType){
                case "SHOW":
                    if (commands.length == 1) {
                        expenseService.showExpense();
                    } else {
                        expenseService.showExpense(userService.getUser(commands[1]),false);
                    }
                    break;
                case "EXPENSE":
                    String paidBy = commands[1];
                    double amount = Double.parseDouble(commands[2]);
                    int noOfUsers = Integer.parseInt(commands[3]);
                    String expenseType = commands[4 + noOfUsers];
                    ExpenseType expenseTypeEnum = ExpenseType.valueOf(expenseType);
                    List<Split> splits = new ArrayList<>();
                    for (int i = 0; i < noOfUsers; i++) {
                        double splitValue = (5 + noOfUsers + i) >= commands.length?0:Double.parseDouble(commands[5 + noOfUsers + i]);
                        splits.add(splitService.getSplit(userService.getUser(commands[4+i]),splitValue,expenseTypeEnum));
                    }
                    expenseService.calculateExpense(userService.getUser(paidBy),amount,splits,expenseTypeEnum);
                    break;
            }
//        }
    }

}
