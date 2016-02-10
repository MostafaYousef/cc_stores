package com.mostafa.cc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mostafa.cc.dto.StoreDTO;
import com.mostafa.cc.dto.StoreInputDTO;
import com.mostafa.cc.facade.StoreFacade;

@Controller
@RequestMapping(value = "/store")
public class StoresController {
	@Autowired
	private StoreFacade storeFacade;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> addStore(@RequestBody StoreInputDTO store) {
		// TODO: Add implementation
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<StoreDTO> getAllStores(@RequestParam(value = "sort", defaultValue = "city") String sort) {
		return storeFacade.getAllStores(sort);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public StoreDTO getStore(@PathVariable("id") String id) {
		return storeFacade.getStore(id);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleException(IllegalArgumentException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
