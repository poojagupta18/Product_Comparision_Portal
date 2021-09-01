package com.sales.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaNamingServerApplication {

	private static final Logger logger = LoggerFactory.getLogger(EurekaNamingServerApplication.class);
	
	public static void main(String[] args) {
		logger.info("START: EurekaNamingServerApplication.main() ");
		SpringApplication.run(EurekaNamingServerApplication.class, args);
		logger.info("END: EurekaNamingServerApplication.main() ");
	}

}
