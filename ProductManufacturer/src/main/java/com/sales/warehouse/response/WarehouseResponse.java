package com.sales.warehouse.response;

import java.util.List;

import com.sales.warehouse.transformingObject.ProductTo;

public class WarehouseResponse {
	
	private String msg;
	private int statusCode;
	private int port;
	private String appName;
	
	private List<ProductTo> productDetails; //to set the any data in response
	
	public WarehouseResponse() {
		
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<ProductTo> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductTo> productDetails) {
		this.productDetails = productDetails;
	}

	@Override
	public String toString() {
		return "WarehouseResponse [msg=" + msg + ", statusCode=" + statusCode + ", port=" + port + ", appName="
				+ appName + ", productDetails=" + productDetails + "]";
	}

	
	
	
	
}
