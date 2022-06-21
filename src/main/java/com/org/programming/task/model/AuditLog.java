package com.org.programming.task.model;

public class AuditLog {

	String ipAddress;

	String dateTime;

	String apiMethod;

	String requestUrl;

	String responseCode;

	String browser;

	public AuditLog(String ipAddress, String dateTime, String apiMethod, String requestUrl, String responseCode) {
		this.ipAddress = ipAddress;
		this.dateTime = dateTime;
		this.apiMethod = apiMethod;
		this.requestUrl = requestUrl;
		this.responseCode = responseCode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getApiMethod() {
		return apiMethod;
	}

	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}

	public String getrequestUrl() {
		return requestUrl;
	}

	public void setrequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Override
	public String toString() {
		return "AuditLog [ipAddress=" + ipAddress + ", dateTime=" + dateTime + ", apiMethod=" + apiMethod + ", requestUrl="
				+ requestUrl + ", responseCode=" + responseCode + "]";
	}

}
