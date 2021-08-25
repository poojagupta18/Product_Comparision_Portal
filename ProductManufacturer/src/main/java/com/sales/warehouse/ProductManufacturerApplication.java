package com.sales.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.sales.warehouse.controller.WarehouseController;

@SpringBootApplication
@EnableDiscoveryClient //For enabling eureka client
public class ProductManufacturerApplication {

	private static final Logger logger = LoggerFactory.getLogger(ProductManufacturerApplication.class);
	
	public static void main(String[] args) {
		logger.info("STRAT: ProductManufacturerApplication");
		SpringApplication.run(ProductManufacturerApplication.class, args);
		logger.info("END: ProductManufacturerApplication");
	}

}
