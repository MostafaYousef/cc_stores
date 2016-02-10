package com.mostafa.cc.service;

import java.text.SimpleDateFormat;
import java.util.List;

import com.mostafa.cc.dto.StoreDTO;

public interface StoresDataService {
	public static final String SEPARATOR = "\"?(,|$)(?=(([^\"]*\"){2})*[^\"]*$) *\"?";
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	public List<StoreDTO> getStores();
}
