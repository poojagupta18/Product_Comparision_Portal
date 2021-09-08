package com.sales.amazon.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sales.amazon.feign.AProductManufacturerProxy;
import com.sales.amazon.impl.AmazonServiceImpl;
import com.sales.amazon.model.AProduct;
import com.sales.amazon.request.AmazonProductRequest;
import com.sales.amazon.response.Response;
import com.sales.amazon.transformingObject.AProductTO;

@RestController
public class AmazonController {

	@Value("${server.port}")
	private int port;

	@Value("${spring.application.name}")
	private String applicationName;

	@Autowired
	AmazonServiceImpl serviceImpl;
	
	@Autowired
	AProductManufacturerProxy proxy;

	private static Logger logger = LoggerFactory.getLogger(AmazonController.class);

	@GetMapping("/amzn/addAllProductFromManufacturer")
	public Response addAllManufacturerProductList() {
		logger.info("START: AmazonController.addAllManufacturerProductList()");
		Response response = new Response();
		
		Response warehouseResonse = proxy.getProductDetailsFromManufacturer();
		
		 List<AProductTO> productList = warehouseResonse.getproductDetails();
		 
		 if (serviceImpl.saveAllWarehouseProducts(productList)) {
				response.setPort(warehouseResonse.getPort());
				response.setAppName(warehouseResonse.getAppName());
				response.setStatusMsg("Warehouse Products added Successfully");
				response.setStatusCode(200);
				response.setSucess(true);
			} else {
				response.setPort(warehouseResonse.getPort());
				response.setAppName(warehouseResonse.getAppName());
				response.setStatusMsg("fail");
				response.setStatusCode(400);
			}
		 

		logger.info("END: AmazonController.addAllManufacturerProductList()");
		return response;
	}

	@GetMapping("/amzn/products")
	public Response getAllAmzonProductList() {
		logger.info("START: AmazonController.getAllAmzonProductList()");
		Response response = new Response();

		List<AProductTO> productList = serviceImpl.getAllAmzonProductList();

		if (!productList.isEmpty()) {
			response.setPort(port);
			response.setAppName(applicationName);
			response.setStatusMsg("Product retrieved Successfully");
			response.setStatusCode(200);
			response.setproductDetails(productList);
			response.setSucess(true);
		} else {
			response.setPort(port);
			response.setAppName(applicationName);
			response.setStatusMsg("fail");
			response.setStatusCode(400);

		}
		logger.info("END: AmazonController.getAllAmzonProductList()");
		return response;
	}

	@GetMapping("/amzn/products/{id}")
	public Response getAmzonProductById(@PathVariable("id") int id) {
		logger.info("START: AmazonController.getAmzonProductById()");
		Response response = new Response();

		List<AProductTO> productList = serviceImpl.getAmzonProductById(id);

		if (!productList.isEmpty()) {
			response.setPort(port);
			response.setAppName(applicationName);
			response.setStatusMsg("Product retrieved Successfully by id");
			response.setStatusCode(200);
			response.setproductDetails(productList);
			response.setSucess(true);
		} else {
			response.setPort(port);
			response.setAppName(applicationName);
			response.setStatusMsg("fail");
			response.setStatusCode(400);

		}

		logger.info("END: AmazonController.getAmzonProductById()");

		return response;
	}

	@PostMapping("/amzn/products")
	public Response createAmazonProduct(@RequestBody List<AmazonProductRequest> productList) {
		logger.info("START: AmazonController.createAmazonProduct()");
		Response response = new Response();

		if (serviceImpl.createAmazonProduct(productList)) {
			response.setPort(port);
			response.setAppName(applicationName);
			response.setStatusMsg("Product added Successfully");
			response.setStatusCode(200);
			response.setSucess(true);
		} else {
			response.setPort(port);
			response.setAppName(applicationName);
			response.setStatusMsg("fail");
			response.setStatusCode(400);
		}

		logger.info("END: AmazonController.createAmazonProduct()");

		return response;
	}

	@PutMapping("/amzn/products/{id}")
	public Response updateAmazonProduct(@PathVariable("id") int id, @RequestBody AProduct myProduct) {
		logger.info("START: AmazonController.updateAmazonProduct()");
		Response response = new Response();

		if (serviceImpl.updateAmazonProduct(myProduct)) {
			response.setPort(port);
			response.setAppName(applicationName);
			response.setStatusMsg("Product updated Successfully");
			response.setStatusCode(200);
			response.setSucess(true);
		} else {
			response.setPort(port);
			response.setAppName(applicationName);
			response.setStatusMsg("fail");
			response.setStatusCode(400);
		}
		logger.info("END: AmazonController.updateAmazonProduct()");
		return response;
	}

	@DeleteMapping("/amzn/products/{id}")
	public Response deleteAmazonProduct(@PathVariable("id") int id) {
		logger.info("START: AmazonController.deleteAmazonProduct()");
		Response response = new Response();

		
		if (serviceImpl.deleteAmazonProduct(id)) {
			response.setPort(port);
			response.setAppName(applicationName);
			response.setStatusMsg("Product deleted Successfully");
			response.setStatusCode(200);
			response.setSucess(true);
		} else {
			response.setPort(port);
			response.setAppName(applicationName);
			response.setStatusMsg("fail");
			response.setStatusCode(400);
		}
		logger.info("END: AmazonController.deleteAmazonProduct()");
		return response;
	}
}
