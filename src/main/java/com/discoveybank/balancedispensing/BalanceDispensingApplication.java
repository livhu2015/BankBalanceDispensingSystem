package com.discoveybank.balancedispensing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BalanceDispensingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalanceDispensingApplication.class, args);
	}

}
