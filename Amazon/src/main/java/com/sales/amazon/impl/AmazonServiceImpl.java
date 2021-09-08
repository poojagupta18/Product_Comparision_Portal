package com.sales.amazon.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.amazon.dao.AmazonDAO;
import com.sales.amazon.model.AProduct;
import com.sales.amazon.request.AmazonProductRequest;
import com.sales.amazon.service.AmazonService;
import com.sales.amazon.transformingObject.AProductTO;

@Service
public class AmazonServiceImpl implements AmazonService {

	static private final Logger logger = LoggerFactory.getLogger(AmazonServiceImpl.class);

	@Autowired
	AmazonDAO dao;

	@Override
	public List<AProductTO> getAllAmzonProductList() {
		logger.info("--START: AmazonServiceImpl.getAllAmzonProductList()--");
		List<AProductTO> productListTO = new ArrayList<AProductTO>();

		try {
			List<AProduct> productList = dao.findAll();
			for (AProduct product : productList) {

				AProductTO to = new AProductTO();

				to.setId(product.getId());
				to.setProductId(product.getProductId());
				to.setProductName(product.getProductName());
				to.setProductPrice(calculateSellingPrice(product.getProductPrice()));
				productListTO.add(to);
			}

		} catch (Exception e) {
			logger.error("Exception occured while retreiving a products from db");
		}

		logger.info("--END: AmazonServiceImpl.getAllAmzonProductList()--");
		return productListTO;
	}

	@Override
	public boolean createAmazonProduct(List<AmazonProductRequest> productList) {
		logger.info("--START: AmazonServiceImpl.createAmazonProduct()--");
		boolean statusFlag = false;
		try {
			List<AProduct> myProduList = new ArrayList<>();

			for (AmazonProductRequest request : productList) {
				AProduct myProduct = new AProduct();
				myProduct.setId(request.getId());
				myProduct.setProductId(request.getProductId());
				myProduct.setProductName(request.getProductName());
				myProduct.setProductPrice(request.getProductPrice());
				myProduList.add(myProduct);
			}
			dao.insert(myProduList);
			statusFlag = true;
		} catch (Exception e) {
			logger.error("Exception occured while inserting a products in db");
		}

		logger.info("--END: AmazonServiceImpl.createAmazonProduct()--");

		return statusFlag;
	}

	@Override
	public boolean updateAmazonProduct(AProduct myProduct) {
		logger.info("--START: AmazonServiceImpl.updateAmazonProduct()--");
		boolean statusFlag = false;
		//try {
			dao.save(myProduct);
			statusFlag = true;
		/*} catch (Exception e) {
			logger.error("Exception occured while updating a products in db");
		}*/
		logger.info("--END: AmazonServiceImpl.updateAmazonProduct()--");

		return statusFlag;
	}

	@Override
	public boolean deleteAmazonProduct( int id) {
		logger.info("--START: AmazonServiceImpl.deleteAmazonProduct()--");
		boolean statusFlag = false;
		try {
			dao.deleteById(id);
			statusFlag = true;
		} catch (Exception e) {
			logger.error("Exception occured while updating a products in db");
		}
		logger.info("--END: AmazonServiceImpl.deleteAmazonProduct()--");

		return statusFlag;
	}

	@Override
	public List<AProductTO> getAmzonProductById(int id) {
		logger.info("--START: AmazonServiceImpl.getAllAmzonProductList()--");
		List<AProductTO> myProductList = new ArrayList<AProductTO>();

		try {
			Optional<AProduct> product = dao.findById(id);

			AProductTO productTo = new AProductTO();
			productTo.setId(product.get().getId());
			productTo.setProductId(product.get().getProductId());
			productTo.setProductName(product.get().getProductName());
			productTo.setProductPrice(product.get().getProductPrice());
			myProductList.add(productTo);

		} catch (Exception e) {
			logger.error("Exception occured while retreiving a products from db");
		}

		logger.info("--END: AmazonServiceImpl.getAllAmzonProductList()--");
		return myProductList;
	}

	private long calculateSellingPrice(long costPrice) {
		long profit = 0L;
		long sellingPrice = 0L;

		if (costPrice <= 199) {
			profit = (costPrice * 50) / 100;
			sellingPrice = costPrice + profit;
		} else if (costPrice >= 199 && costPrice >= 499) {
			profit = (costPrice * 40) / 100;
			sellingPrice = costPrice + profit;
		} else {
			profit = (costPrice * 10) / 100;
			sellingPrice = costPrice + profit;
		}
		return sellingPrice;
	}

	@Override
	public boolean saveAllWarehouseProducts(List<AProductTO> productList) {
		boolean statusFlag = false;
		try {
			List<AProduct> myProduList = new ArrayList<>();

			for (AProductTO request : productList) {
				AProduct myProduct = new AProduct();
				myProduct.setId(request.getId());
				myProduct.setProductId(request.getProductId());
				myProduct.setProductName(request.getProductName());
				myProduct.setProductPrice(calculateSellingPrice(request.getProductPrice()));
				myProduList.add(myProduct);
			}
			dao.insert(myProduList);
			statusFlag = true;
		} catch (Exception e) {
			logger.error("Exception occured while inserting a products in db");
		}
				
		return statusFlag;
	}
}
