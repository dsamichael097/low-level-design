package com.example.lowleveldesign.splitwise.controller;

import com.example.lowleveldesign.splitwise.service.InputHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SplitwiseController {

    @Autowired
    private InputHandlerService inputHandlerService;

    @PostMapping("/input")
    public void handleInput(@RequestBody String input){
        /*
        * Input types
        * SHOW
        * SHOW u1
        * EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
        * EXPENSE u1 1250 2 u2 u3 EXACT 370 880
        * EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
        * */
        inputHandlerService.handleInput(input);
    }
}
