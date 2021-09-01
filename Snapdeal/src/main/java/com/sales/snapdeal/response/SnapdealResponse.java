package com.sales.snapdeal.response;

import java.util.List;

import com.sales.snapdeal.transformobject.SnapdealTo;

public class SnapdealResponse {

	private String msg;
	private int port;
	private boolean isSucess;
	private int statusCode;
	private String appName;
	private List<SnapdealTo> productDetails;

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

	public List<SnapdealTo> getproductDetails() {
		return productDetails;
	}

	public void setproductDetails(List<SnapdealTo> to) {
		this.productDetails = to;
	}

}
