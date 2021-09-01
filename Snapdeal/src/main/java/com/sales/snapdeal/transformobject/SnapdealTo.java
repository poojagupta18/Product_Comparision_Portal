package com.sales.snapdeal.transformobject;

public class SnapdealTo {
	private int id;
	private String wproductId;
	private String wproductName;
	private long wproductPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductId() {
		return wproductId;
	}

	public void setProductId(String productId) {
		this.wproductId = productId;
	}

	public String getProductName() {
		return wproductName;
	}

	public void setProductName(String productName) {
		this.wproductName = productName;
	}

	public long getProductPrice() {
		return wproductPrice;
	}

	public void setProductPrice(long productPrice) {
		this.wproductPrice = productPrice;
	}

	@Override
	public String toString() {
		return "SnapdealProduct [id=" + id + ", productId=" + wproductId + ", productName=" + wproductName
				+ ", productPrice=" + wproductPrice + "]";
	}
}
