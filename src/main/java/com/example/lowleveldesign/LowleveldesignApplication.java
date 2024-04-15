package com.example.lowleveldesign;

import com.example.lowleveldesign.splitwise.service.InputHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LowleveldesignApplication {

	@Autowired
	private InputHandlerService inputHandlerService;

	public static void main(String[] args) {
		SpringApplication.run(LowleveldesignApplication.class, args);
	}

}
