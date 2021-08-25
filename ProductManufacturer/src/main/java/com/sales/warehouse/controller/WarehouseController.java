package com.sales.warehouse.controller;

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

import com.sales.warehouse.model.WarehouseProduct;
import com.sales.warehouse.request.WarehouseRequest;
import com.sales.warehouse.response.WarehouseResponse;
import com.sales.warehouse.service.impl.WarehouseServiceImpl;
import com.sales.warehouse.transformingObject.ProductTo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8080/swagger-ui.html
//Swagger for controller level
@Api(value = "WarehouseController", description = "This controller is used to save, retrieve, update or delete the products in warehouse. ")
@RestController
public class WarehouseController {

	private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

	@Autowired
	WarehouseServiceImpl service;

	@Value("${server.port}")
	int port;

	@Value("${spring.application.name}")
	String appName;

	@ApiOperation(value = "Saves the product details", tags = "product") // swagger for service level
	@PostMapping("warehouse/products")
	public WarehouseResponse createProduct(@RequestBody List<WarehouseRequest> productList) {
		logger.info("START WarehouseController.createProduct() request{} " + productList);

		WarehouseResponse response = new WarehouseResponse();
		String statusMsg = "";

		try {
			statusMsg = service.createProduct(productList);
			response.setMsg(statusMsg);
			response.setPort(port);
			response.setAppName(appName);
			response.setStatusCode(200);
		} catch (Exception e) {
			response.setMsg(statusMsg);
			response.setPort(port);
			response.setAppName(appName);
			response.setStatusCode(400);
		}
		logger.info("END WarehouseController.createProduct() reponse {} " + response);

		return response;
	}

	@ApiOperation(value = "Retrive all product details", tags = "product")
	@GetMapping("warehouse/products")
	public WarehouseResponse getAllProduct() {
		logger.info("START: WarehouseController.getAllProduct() ");
		WarehouseResponse response = new WarehouseResponse();
		try {
			List<ProductTo> productLits = service.getAllProduct();
			response.setMsg("Sucess");
			response.setPort(port);
			response.setAppName(appName);
			response.setStatusCode(200);
			response.setProductDetails(productLits);
		} catch (Exception e) {
			logger.error("Exception occured while retrieving a product details.");
			response.setMsg("Failed");
			response.setPort(port);
			response.setAppName(appName);
			response.setStatusCode(400);
		}
		logger.info("END: WarehouseController.getAllProduct() Response {} " + response);

		return response;

	}

	@ApiOperation(value = "Retrive product details by using productId", tags = "product")
	@GetMapping("warehouse/products/{productId}")
	public WarehouseResponse getProductById(@PathVariable("productId") int productId) {
		logger.info("START: WarehouseController.getAllProduct() ");
		WarehouseResponse response = new WarehouseResponse();

		try {
			List<ProductTo> productList = service.getProductById(productId);
			response.setMsg("Sucess");
			response.setPort(port);
			response.setAppName(appName);
			response.setStatusCode(200);
			response.setProductDetails(productList);
		} catch (Exception e) {
			logger.error("Exception occured while retrieving a product details.");
			response.setMsg("Failed");
			response.setPort(port);
			response.setAppName(appName);
			response.setStatusCode(400);
		}
		logger.info("END: WarehouseController.updateProduct() ");

		return response;
	}

	@ApiOperation(value = "Update product details by using productId", tags = "product")
	@PutMapping("warehouse/products/{productId}")
	public WarehouseResponse updateProduct(@PathVariable("productId") String productId,
			WarehouseProduct warehouseProduct) {
		logger.info("START: WarehouseController.updateProduct() ");
		WarehouseResponse response = new WarehouseResponse();
		WarehouseProduct product = new WarehouseProduct();
		String statusMsg = "";

		// As save method of mongoDb only accepts the WarehouseProduct type object as
		// MongoRepository<WarehouseProduct, INTEGER> was given in model
		product.setId(warehouseProduct.getId());
		product.setProductId(warehouseProduct.getProductId());
		product.setProductName(warehouseProduct.getProductName());
		product.setProductPrice(warehouseProduct.getProductPrice());

		try {
			statusMsg = service.updateProduct(product);
			response.setMsg(statusMsg);
			response.setPort(port);
			response.setAppName(appName);
			response.setStatusCode(200);

		} catch (Exception e) {
			logger.error("Exception occured while updating product details.");
			response.setMsg(statusMsg);
			response.setPort(port);
			response.setAppName(appName);
			response.setStatusCode(200);
		}

		logger.info("END: WarehouseController.updateProduct() ");
		return response;
	}

	@ApiOperation(value = "Delete product by using productId", tags = "product")
	@DeleteMapping("warehouse/products/{id}")
	public WarehouseResponse deleteProduct(@PathVariable("id") int id) {
		logger.info("START: WarehouseController.deleteProduct() ");

		WarehouseResponse response = new WarehouseResponse();
		String statusMsg = "";

		try {
			statusMsg = service.deleteProduct(id);
			response.setMsg(statusMsg);
			response.setPort(port);
			response.setAppName(appName);
			response.setStatusCode(200);

		} catch (Exception e) {
			logger.error("Exception occured while Deleting a product details.");
			response.setMsg(statusMsg);
			response.setPort(port);
			response.setAppName(appName);
			response.setStatusCode(200);
		}
		logger.info("END: WarehouseController.deleteProduct() ");
		return response;
	}
}
