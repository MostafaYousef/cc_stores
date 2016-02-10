package com.mostafa.cc.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mostafa.cc.dto.StoreDTO;
import com.mostafa.cc.service.StoresDataService;

@Component
public class StoreFacade {
	private StoresDataService storesDataService;

	@Autowired
	public StoreFacade(StoresDataService storesDataService) {
		this.storesDataService = storesDataService;
	}

	public List<StoreDTO> getAllStores(String sort) {
		if (StringUtils.isEmpty(sort)) {
			throw new IllegalArgumentException("Sort cannot be empty!");
		}

		// copy the stores list
		List<StoreDTO> stores = new ArrayList<>(storesDataService.getStores());

		Collections.sort(stores, getComparator(sort));
		return stores;
	}

	public StoreDTO getStore(String storeId) {
		if (StringUtils.isEmpty(storeId)) {
			throw new IllegalArgumentException("Store id cannot be empty!");
		}

		List<StoreDTO> stores = storesDataService.getStores();
		for (StoreDTO store : stores) {
			if (storeId.equals(store.getStoreId())) {
				return store;
			}
		}
		throw new IllegalArgumentException("Could not find store with storeid: " + storeId);
	}

	private Comparator<StoreDTO> getComparator(String sort) {
		Comparator<StoreDTO> comparator = null;

		if (sort.matches("\\-?(city)")) {
			comparator = new CityComparator();
		} else if (sort.matches("\\-?(date)")) {
			comparator = new DateComparator();
		} else {
			throw new IllegalArgumentException(sort + " is not a valid sort parameter");
		}

		return sort.startsWith("-") ? Collections.reverseOrder(comparator) : comparator;
	}

	class CityComparator implements Comparator<StoreDTO> {
		@Override
		public int compare(StoreDTO store1, StoreDTO store2) {
			return store1.getCity().compareTo(store2.getCity());
		}
	}

	class DateComparator implements Comparator<StoreDTO> {
		@Override
		public int compare(StoreDTO store1, StoreDTO store2) {
			return Integer.compare(store1.getDaysOpened(), store2.getDaysOpened());
		}
	}
}