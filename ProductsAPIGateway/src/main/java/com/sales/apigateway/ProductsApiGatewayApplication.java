package com.sales.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ProductsApiGatewayApplication {

	private static final Logger logger = LoggerFactory.getLogger(ProductsApiGatewayApplication.class);
	public static void main(String[] args) {
		logger.info("STRAT: ProductsApiGatewayApplication.main()");
		SpringApplication.run(ProductsApiGatewayApplication.class, args);
		logger.info("END: ProductsApiGatewayApplication.main()");

	}

}
