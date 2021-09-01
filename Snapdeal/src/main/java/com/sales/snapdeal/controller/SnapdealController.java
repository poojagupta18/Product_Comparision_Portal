package com.sales.snapdeal.controller;

import java.util.ArrayList;
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

import com.sales.snapdeal.feign.SProductManufacturerProxy;
import com.sales.snapdeal.impl.SnapdealServiceImpl;
import com.sales.snapdeal.model.SnapdealProduct;
import com.sales.snapdeal.request.SnapdealRequest;
import com.sales.snapdeal.response.SnapdealResponse;
import com.sales.snapdeal.transformobject.SnapdealTo;

@RestController
public class SnapdealController {
	@Value("${server.port}")
	int port;

	@Autowired
	SnapdealServiceImpl snapdealServiceImpl;

	@Autowired
	SProductManufacturerProxy manufacturerProxy;

	private static final Logger logger = LoggerFactory.getLogger(SnapdealController.class);

	@PostMapping("snapdeal/products")
	public SnapdealResponse createSnapDealProduct(@RequestBody List<SnapdealRequest> myProductList) {

		logger.info("STRAT: SnapdealController.createSnapDealProduct() Request {} " + myProductList);
		String statusMsg = "";
		SnapdealResponse response = new SnapdealResponse();
		List<SnapdealProduct> snapdealProductList = new ArrayList<SnapdealProduct>();

		for (SnapdealRequest myProduct : myProductList) {
			SnapdealProduct product = new SnapdealProduct();

			product.setId(myProduct.getId());
			product.setProductId(myProduct.getProductId());
			product.setProductName(myProduct.getProductName());
			product.setProductPrice(myProduct.getProductPrice());

			snapdealProductList.add(product);
		}

		statusMsg = snapdealServiceImpl.createSnapDealProduct(snapdealProductList);

		if ("SUCCESS".equals(statusMsg)) {
			response.setStatusMsg("Product Added successFully in DB");
			response.setStatusCode(200);
			response.setSucess(true);
			response.setPort(port);
		} else {
			response.setStatusMsg("Product Addition failed");
			response.setStatusCode(400);
			response.setSucess(false);
			response.setPort(port);
		}
		logger.info("END: SnapdealController.createSnapDealProduct()");

		return response;
	}

	@GetMapping("snapdeal/products")
	public SnapdealResponse getAllProducts() {
		logger.info("STRAT: SnapdealController.getAllProducts() ");
		SnapdealResponse response = new SnapdealResponse();
		List<SnapdealProduct> snapdealProductList = snapdealServiceImpl.getAllProducts();

		if (!snapdealProductList.isEmpty()) {

			List<SnapdealTo> snapdealProductToList = new ArrayList<SnapdealTo>();

			for (SnapdealProduct fetchedProduct : snapdealProductList) {
				SnapdealTo product = new SnapdealTo();
				product.setId(fetchedProduct.getId());
				product.setProductId(fetchedProduct.getProductId());
				product.setProductName(fetchedProduct.getProductName());
				product.setProductPrice(fetchedProduct.getProductPrice());
				snapdealProductToList.add(product);
			}

			response.setproductDetails(snapdealProductToList);
			response.setStatusCode(200);
			response.setStatusMsg("SUCCESS");
			response.setPort(port);
			response.setSucess(true);
		} else {

			response.setStatusCode(400);
			response.setStatusMsg("Error while fetching the product");
			response.setPort(port);
			response.setSucess(false);
		}
		logger.info("END: SnapdealController.getAllProducts()");
		return response;
	}

	@GetMapping("snapdeal/products/{id}")
	public SnapdealResponse getProductById(@PathVariable("id") int id) {
		logger.info("STRAT: SnapdealController.getProductById() ");
		SnapdealResponse response = new SnapdealResponse();
		List<SnapdealProduct> snapdealProductList = snapdealServiceImpl.getProductById(id);

		if (!snapdealProductList.isEmpty()) {

			List<SnapdealTo> snapdealProductToList = new ArrayList<SnapdealTo>();

			for (SnapdealProduct fetchedProduct : snapdealProductList) {
				SnapdealTo product = new SnapdealTo();
				product.setId(fetchedProduct.getId());
				product.setProductId(fetchedProduct.getProductId());
				product.setProductName(fetchedProduct.getProductName());
				product.setProductPrice(fetchedProduct.getProductPrice());
				snapdealProductToList.add(product);
			}

			response.setproductDetails(snapdealProductToList);
			response.setStatusCode(200);
			response.setStatusMsg("SUCCESS");
			response.setPort(port);
			response.setSucess(true);
		} else {

			response.setStatusCode(400);
			response.setStatusMsg("Error while fetching the product");
			response.setPort(port);
			response.setSucess(false);
		}
		logger.info("END: SnapdealController.getProductById()");
		return response;

	}

	@DeleteMapping("snapdeal/products/{id}")
	public SnapdealResponse deleteProdctById(@PathVariable("id") int id) {
		logger.info("STRAT: SnapdealController.deleteProdctById() ");
		SnapdealResponse response = new SnapdealResponse();

		boolean flag = snapdealServiceImpl.deleteProdctById(id);

		if (flag) {
			response.setStatusMsg("Product deleted successfully");
			response.setStatusCode(200);
			response.setSucess(true);
			response.setPort(port);
		} else {
			response.setStatusMsg("Product deletion failed");
			response.setStatusCode(400);
			response.setSucess(false);
			response.setPort(port);
		}
		logger.info("END: SnapdealController.deleteProdctById()");
		return response;
	}

	@PutMapping("snapdeal/products/{id}")
	public SnapdealResponse updateProductById(@PathVariable("id") int id, @RequestBody SnapdealRequest myProduct) {
		logger.info("STRAT: SnapdealController.updateProductById() ");
		SnapdealResponse response = new SnapdealResponse();

		SnapdealProduct product = new SnapdealProduct();

		product.setId(myProduct.getId());
		product.setProductId(myProduct.getProductId());
		product.setProductName(myProduct.getProductName());
		product.setProductPrice(myProduct.getProductPrice());

		boolean flag = snapdealServiceImpl.updateProductById(id, product);

		if (flag) {
			response.setStatusMsg("Product updated successfully");
			response.setStatusCode(200);
			response.setSucess(true);
			response.setPort(port);
		} else {
			response.setStatusMsg("Product updation failed");
			response.setStatusCode(400);
			response.setSucess(false);
			response.setPort(port);
		}
		logger.info("END: SnapdealController.updateProductById()");
		return response;
	}

	/***
	 * Take all the product from product Manufacturer and save it in SnapdealDB
	 * 
	 * @param manufacturerProductDetails
	 * @return
	 */
	@GetMapping("snapdeal/addAllProductFromManufacturer")
	public SnapdealResponse addProductFromManufacturer(List<SnapdealProduct> manufacturerProductDetails) {
		logger.info("STRAT: SnapdealController.addProductFromManufacturer() ");
		SnapdealResponse myResponse = new SnapdealResponse();
		SnapdealResponse response = manufacturerProxy.getProductDetailsFromManufacturer();
		logger.info("Response from Manufacturer proxy {} " + response);

		List<SnapdealTo> manufacturerDetails = response.getproductDetails();

		if (snapdealServiceImpl.addProductFromManufacturer(manufacturerDetails)) {
			myResponse.setStatusMsg("Warehouse Product added successfully in snapdeal db");
			myResponse.setStatusCode(200);
			myResponse.setSucess(true);
			myResponse.setPort(port);
		} else {
			myResponse.setStatusMsg("Warehouse Product addition failed");
			myResponse.setStatusCode(400);
			myResponse.setSucess(false);
			myResponse.setPort(port);
		}

		logger.info("END: SnapdealController.addProductFromManufacturer()");
		return myResponse;
	}
}
