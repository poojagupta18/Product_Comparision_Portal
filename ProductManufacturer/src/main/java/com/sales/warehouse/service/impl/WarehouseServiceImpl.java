package com.sales.warehouse.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.warehouse.dao.WarehouseDAO;
import com.sales.warehouse.model.WarehouseProduct;
import com.sales.warehouse.request.WarehouseRequest;
import com.sales.warehouse.service.WarehouseService;
import com.sales.warehouse.transformingObject.ProductTo;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);
	@Autowired
	WarehouseDAO dao;

	@Override
	public String createProduct(List<WarehouseRequest> myProduct) {
		logger.info("START: WarehouseServiceImpl.createProduct() service request {}" + myProduct);
		String statusMsg = "";
		List<WarehouseProduct> toProductList = new ArrayList<WarehouseProduct>();

		for (WarehouseRequest warehouseRequest : myProduct) {
			WarehouseProduct to = new WarehouseProduct();
			to.setId(warehouseRequest.getId());
			to.setProductId(warehouseRequest.getProductId());
			to.setProductName(warehouseRequest.getProductName());
			to.setProductPrice(warehouseRequest.getProductPrice());
			toProductList.add(to);
		}
		try {

			dao.insert(toProductList);
			statusMsg = "Data inserted successfully";
		} catch (Exception e) {
			statusMsg = "Exception occured while inserting a data";
			logger.error(statusMsg);
		}
		logger.info("END: WarehouseServiceImpl.createProduct()");
		return statusMsg;
	}

	@Override
	public List<ProductTo> getAllProduct() {
		logger.info("START: WarehouseServiceImpl.getAllProduct() service");
		List<ProductTo> produtList = new ArrayList<ProductTo>();
		String msg = "";

		List<WarehouseProduct> myproduct = dao.findAll();

		for (WarehouseProduct warehouseProduct : myproduct) {
			ProductTo to = new ProductTo();
			to.setId(warehouseProduct.getId());
			to.setProductId(warehouseProduct.getProductId());
			to.setProductName(warehouseProduct.getProductName());
			to.setProductPrice(warehouseProduct.getProductPrice());
			produtList.add(to);
		}

		logger.info("END: WarehouseServiceImpl.getAllProduct() service response {} " + produtList );
		return produtList;
	}

	@Override
	public List<ProductTo> getProductById(int productId) {
		logger.info("STRAT: WarehouseServiceImpl.getProductById() service requset " + productId );
		List<ProductTo> produtList = new ArrayList<ProductTo>();
		ProductTo to = new ProductTo();
		try {
			Optional<WarehouseProduct> warehouseProduct = dao.findById(productId);
			
			to.setId(warehouseProduct.get().getId());
			to.setProductId(warehouseProduct.get().getProductId());
			to.setProductName(warehouseProduct.get().getProductName());
			to.setProductPrice(warehouseProduct.get().getProductPrice());
			produtList.add(to);
		} catch (Exception e) {
			logger.error("Exception while retrieving the product details.");
		}
		logger.info("END: WarehouseServiceImpl.getAllProduct() service  response" + to);

		return produtList;
	}

	@Override
	public String updateProduct(WarehouseProduct product) {
		String statusMsg = "";
		try {
			dao.save(product);
			statusMsg = "Warehouse product updated successfully";
		} catch (Exception e) {
			statusMsg = "Exception occured while updating the product";
		}
		return statusMsg;
	}

	@Override
	public String deleteProduct(int productId) {
		String statusMsg = "";
		try {
			dao.deleteById(productId);
			statusMsg = "Warehouse product deleted successfully";
		} catch (Exception e) {
			statusMsg = "Exception occured while deleting the product";
		}
		return statusMsg;

	}

}
