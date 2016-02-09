package com.mostafa.cc.dto;

public class StoreDTO {
	private String storeId;
	private String postCode;
	private String city;
	private String address;
	private String openedDate;
	private int daysOpened;
	
	public String getStoreId() {
		return storeId;
	}
	
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getOpenedDate() {
		return openedDate;
	}
	
	public void setOpenedDate(String openedDate) {
		this.openedDate = openedDate;
	}

	public int getDaysOpened() {
		return daysOpened;
	}

	public void setDaysOpened(int daysOpened) {
		this.daysOpened = daysOpened;
	}
}
