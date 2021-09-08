package com.sales.amazon.service;

import java.util.List;

import com.sales.amazon.model.AProduct;
import com.sales.amazon.request.AmazonProductRequest;
import com.sales.amazon.transformingObject.AProductTO;

public interface AmazonService {

	List<AProductTO> getAllAmzonProductList();
	
	List<AProductTO> getAmzonProductById(int id);

	boolean createAmazonProduct(List<AmazonProductRequest> productList);

	boolean updateAmazonProduct(AProduct myProduct);

	boolean deleteAmazonProduct(int id);
	
	boolean saveAllWarehouseProducts(List<AProductTO> productList);

}
