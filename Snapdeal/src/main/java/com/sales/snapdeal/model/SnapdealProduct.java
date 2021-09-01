package com.sales.snapdeal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SnapdealProducts")
public class SnapdealProduct {

	@Id
	private int id;

	private String productId;

	private String productName;

	private long  productPrice;

	
	
	public SnapdealProduct() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "SnapdealProduct [id=" + id + ", productId=" + productId + ", productName=" + productName
				+ ", productPrice=" + productPrice + "]";
	}

}
