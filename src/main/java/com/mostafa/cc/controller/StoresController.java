package com.mostafa.cc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.mostafa.cc.dto.StoreDTO;
import com.mostafa.cc.facade.StoreFacade;

@Controller
public class StoresController {
	@Autowired
	private StoreFacade storeFacade;

	@RequestMapping(value = "/stores", method = RequestMethod.GET)
	@ResponseBody
	public List<StoreDTO> getAllStores(@RequestParam(value = "sort", defaultValue = "city") String sort) {
		return storeFacade.getAllStores(sort);
	}

	@RequestMapping(value = "/stores/{id}", method = RequestMethod.GET)
	@ResponseBody
	public StoreDTO getStore(@PathVariable("id") String id) {
		return storeFacade.getStore(id);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleException(IllegalArgumentException exception) {
		return exception.getMessage();
	}
}
