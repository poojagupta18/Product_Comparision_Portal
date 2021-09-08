package com.sales.amazon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//http://localhost:8078/swagger-ui.html
@EnableFeignClients("com.sales.amazon")
@SpringBootApplication
public class AmazonApplication {

	private static final Logger logger = LoggerFactory.getLogger(AmazonApplication.class);

	public static void main(String[] args) {

		logger.info("STRAT: AmazonApplication.main()");
		SpringApplication.run(AmazonApplication.class, args);
		logger.info("END: AmazonApplication.main()");

	}

}
