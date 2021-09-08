package com.sales.amazon.response;

import java.util.List;

import com.sales.amazon.transformingObject.AProductTO;

public class Response {
	private String msg;
	private int port;
	private boolean isSucess;
	private int statusCode;
	private String appName;
	private List<AProductTO> productDetails;

	public String getStatusMsg() {
		return msg;
	}

	public void setStatusMsg(String statusMsg) {
		this.msg = statusMsg;
	}

	public int getPort() {
		return port;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isSucess() {
		return isSucess;
	}

	public void setSucess(boolean isSucess) {
		this.isSucess = isSucess;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List<AProductTO> getproductDetails() {
		return productDetails;
	}

	public void setproductDetails(List<AProductTO> to) {
		this.productDetails = to;
	}

}
