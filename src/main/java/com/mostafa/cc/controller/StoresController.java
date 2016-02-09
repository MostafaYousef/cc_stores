package com.mostafa.cc.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mostafa.cc.dto.StoreDTO;

@Controller
public class StoresController {

	@RequestMapping(value = "/stores", method = RequestMethod.GET)
	@ResponseBody
	public List<StoreDTO> getAllStores(@RequestParam(value = "sort", defaultValue = "city") String sortBy) {
		List<StoreDTO> list = new ArrayList<>();
		
		StoreDTO dto = new StoreDTO();
		dto.setStoreId(sortBy);
		list.add(dto);
		
		StoreDTO dto2 = new StoreDTO();
		dto2.setStoreId(sortBy);
		list.add(dto2);
		
		return list;
	}

	@RequestMapping(value = "/stores/{id}", method = RequestMethod.GET)
	@ResponseBody
	public StoreDTO getStore(@PathVariable("id") String id) {
		StoreDTO dto = new StoreDTO();
		dto.setStoreId(id);
		return dto;
	}
}
