package com.sales.amazon.request;

public class AmazonProductRequest {
	
	private int id;

	private String productId;

	private String productName;

	private long productPrice;

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
		return "AmazonProductRequest [id=" + id + ", productId=" + productId + ", productName=" + productName
				+ ", productPrice=" + productPrice + "]";
	}


}
