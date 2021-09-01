package com.sales.snapdeal.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.sales.snapdeal.dao.SnapdealDAO;
import com.sales.snapdeal.model.SnapdealProduct;
import com.sales.snapdeal.response.SnapdealResponse;
import com.sales.snapdeal.service.SnapdealService;
import com.sales.snapdeal.transformobject.SnapdealTo;

@Service
public class SnapdealServiceImpl implements SnapdealService {

	private static final Logger logger = LoggerFactory.getLogger(SnapdealServiceImpl.class);

	@Autowired
	SnapdealDAO snapdealDao;

	@Override
	public String createSnapDealProduct(List<SnapdealProduct> myProduct) {

		logger.info("STRAT: SnapdealServiceImpl.createSnapDealProduct() Request {} " + myProduct);
		String statusMsg = "";
		try {
			snapdealDao.insert(myProduct);
			statusMsg = "SUCCESS";
		} catch (Exception e) {
			logger.error("Exception occured while inserting Product.");
			statusMsg = "FAIL";
		}
		logger.info("END: SnapdealServiceImpl.createSnapDealProduct()");

		return statusMsg;
	}

	@Override
	public List<SnapdealProduct> getAllProducts() {
		List<SnapdealProduct> productList = new ArrayList<SnapdealProduct>();
		logger.info("STRAT: SnapdealServiceImpl.getAllProducts() ");
		try {
			productList = snapdealDao.findAll();
		} catch (Exception e) {
			logger.error("Exception occured while fetching Product.");
		}
		logger.info("END: SnapdealServiceImpl.getAllProducts()");
		return productList;
	}

	@Override
	public List<SnapdealProduct> getProductById(int id) {
		List<SnapdealProduct> productList = new ArrayList<SnapdealProduct>();
		logger.info("STRAT: SnapdealServiceImpl.getProductById() ");
		try {
			Optional<SnapdealProduct> snapDealProductList = snapdealDao.findById(id);

			SnapdealProduct product = new SnapdealProduct();
			product.setId(snapDealProductList.get().getId());
			product.setProductId(snapDealProductList.get().getProductId());
			product.setProductName(snapDealProductList.get().getProductName());
			product.setProductPrice(snapDealProductList.get().getProductPrice());

			productList.add(product);

		} catch (Exception e) {
			logger.error("Exception occured while fetching Product.");
		}
		logger.info("END: SnapdealServiceImpl.getProductById()");
		return productList;
	}

	@Override
	public boolean deleteProdctById(int id) {
		boolean statusFlag = false;
		logger.info("STRAT: SnapdealServiceImpl.deleteProdctById() ");
		try {
			snapdealDao.deleteById(id);
			statusFlag = true;
		} catch (Exception e) {
			logger.error("Exception occured while deleting the Product.");
		}
		logger.info("END: SnapdealServiceImpl.deleteProdctById()");
		return statusFlag;
	}

	@Override
	public boolean updateProductById(int id, SnapdealProduct myProduct) {
		boolean statusFlag = false;
		logger.info("STRAT: SnapdealServiceImpl.updateProductById() ");
		try {
			snapdealDao.save(myProduct);
			statusFlag = true;
		} catch (Exception e) {
			logger.error("Exception occured while updating the Product.");
		}
		logger.info("END: SnapdealServiceImpl.updateProductById()");
		return statusFlag;
	}

	@Override
	public boolean addProductFromManufacturer(List<SnapdealTo> manufacturerProductDetails) {
		logger.info("STRAT: SnapdealServiceImpl.addProductFromManufacturer() ");
		boolean statusFlag = false;

		List<SnapdealProduct> manufacturerProductList = new ArrayList<SnapdealProduct>();

		for (SnapdealTo manufacturingProduct : manufacturerProductDetails) {
			SnapdealProduct product = new SnapdealProduct();
			product.setId(manufacturingProduct.getId());
			product.setProductId(manufacturingProduct.getProductId());
			product.setProductName(manufacturingProduct.getProductName());
			product.setProductPrice(calculateSellingPrice(manufacturingProduct.getProductPrice()));

			manufacturerProductList.add(product);

		}

		try {
			snapdealDao.insert(manufacturerProductList);
			statusFlag = true;
		} catch (Exception e) {
			logger.error("Exception occured while adding warehouse Product.");
		}

		logger.info("END: SnapdealServiceImpl.addProductFromManufacturer()");
		return statusFlag;
	}

	private long calculateSellingPrice(long costPrice) {
		long profit = 0L;
		long sellingPrice = 0L;

		if (costPrice <= 199) {
			profit = (costPrice * 60) / 100;
			sellingPrice = costPrice + profit;
		} else if (costPrice >= 199 && costPrice >= 499) {
			profit = (costPrice * 40) / 100;
			sellingPrice = costPrice + profit;
		} else {
			profit = (costPrice * 20) / 100;
			sellingPrice = costPrice + profit;
		}
		return sellingPrice;
	}

}
