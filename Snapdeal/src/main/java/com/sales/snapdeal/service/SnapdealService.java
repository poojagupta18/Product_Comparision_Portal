package com.sales.snapdeal.service;

import java.util.List;

import com.sales.snapdeal.model.SnapdealProduct;
import com.sales.snapdeal.transformobject.SnapdealTo;

public interface SnapdealService{

	boolean addProductFromManufacturer(List<SnapdealTo> manufacturerProductDetails);
	
	String createSnapDealProduct(List<SnapdealProduct> myProduct);
	
	List<SnapdealProduct> getAllProducts();
	
	List<SnapdealProduct> getProductById(int id);
	
	boolean deleteProdctById(int id);
	
	boolean updateProductById(int id, SnapdealProduct myProduct);
}
