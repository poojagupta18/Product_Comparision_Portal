package com.sales.warehouse.transformingObject;

public class ProductTo {
private int id;
	
	private String productId;
	private String productName;
	private int productPrice;


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

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "ProductRequest [productId=" + productId + ", productName=" + productName + ", productPrice="
				+ productPrice + "]";
	}

}
