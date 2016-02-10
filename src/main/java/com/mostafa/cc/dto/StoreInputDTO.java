package com.mostafa.cc.dto;

public class StoreInputDTO {
	private String storeId;
	private String postCode;
	private String city;
	private String address;
	private String openedDate;

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

	@Override
	public String toString() {
		return "StoreInputDTO [storeId=" + storeId + ", postCode=" + postCode + ", city=" + city + ", address="
				+ address + ", openedDate=" + openedDate + "]";
	}
}
