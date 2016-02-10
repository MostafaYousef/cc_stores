package com.mostafa.cc.dto;

public class StoreDTO {
	private String storeId;
	private String postCode;
	private String city;
	private String address;
	private String openedDate;
	private int daysOpened;
	
	public StoreDTO(String storeId, String postCode, String city, String address, String openedDate, int daysOpened) {
		this.storeId = storeId;
		this.postCode = postCode;
		this.city = city;
		this.address = address;
		this.openedDate = openedDate;
		this.daysOpened = daysOpened;
	}

	public String getStoreId() {
		return storeId;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getOpenedDate() {
		return openedDate;
	}
	
	public int getDaysOpened() {
		return daysOpened;
	}

	@Override
	public String toString() {
		return "StoreDTO [storeId=" + storeId + ", postCode=" + postCode + ", city=" + city + ", address=" + address
				+ ", openedDate=" + openedDate + ", daysOpened=" + daysOpened + "]";
	}
}
