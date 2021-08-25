package com.sales.warehouse.service;

import java.util.List;

import com.sales.warehouse.model.WarehouseProduct;
import com.sales.warehouse.request.WarehouseRequest;
import com.sales.warehouse.transformingObject.ProductTo;

public interface WarehouseService {

	String createProduct(List<WarehouseRequest> requestProduct);

	List<ProductTo> getAllProduct();

	List<ProductTo> getProductById(int productId);

	String updateProduct(WarehouseProduct product);

	String deleteProduct(int productId);

}
