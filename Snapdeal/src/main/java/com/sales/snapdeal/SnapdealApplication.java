package com.sales.snapdeal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.sales.snapdeal.controller.SnapdealController;
//localhost:8380
@SpringBootApplication
@EnableFeignClients("com.sales.snapdeal")
public class SnapdealApplication {

	private static final Logger logger = LoggerFactory.getLogger(SnapdealApplication.class);

	
	public static void main(String[] args) {
		logger.info("STRAT: SnapdealApplication.main()");

		SpringApplication.run(SnapdealApplication.class, args);
		logger.info("STRAT: SnapdealApplication.main()");

	}

}
