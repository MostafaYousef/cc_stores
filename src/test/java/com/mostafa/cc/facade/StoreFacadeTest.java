package com.mostafa.cc.facade;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mostafa.cc.dto.StoreDTO;
import com.mostafa.cc.service.StoresDataService;

import static org.junit.Assert.*;

public class StoreFacadeTest {
	private StoreFacade facade;

	private static final StoreDTO STORE_BIRMINGHAM = new StoreDTO("a793f4a0-068c-45f4-8570-d172396621a2", "B24 9FP", "Birmingham", "Unit 11a The Fort Shipping Park, Fort Park Way", "28/06/1993", 8261);
	private static final StoreDTO STORE_GLASGOW = new StoreDTO("3fc8b9b0-f527-43d3-9628-5f0dde988986", "G53 6QR", "Glasgow", "Unit B8 Silverburn Shopping Centre", "02/06/1973", 15592);
	private static final StoreDTO STORE_LONDON = new StoreDTO("42fb44ca-ffb3-46b0-aeab-f6985c23a546", "BR1 1EA", "London", "12-14 High Street, Bromley", "19/05/1957", 21450);
	private static final StoreDTO STORE_SWANGE = new StoreDTO("e2b88cf1-508d-4def-a791-516fa4569fca", "BH19 2NT", "Swange", "High Street, The Trocadero", "04/11/1990", 9228);
	private static final StoreDTO STORE_WITHAM = new StoreDTO("a1fa2b11-7079-4230-a337-5bb043f78828", "CM8 2AQ", "Witham", "Units 20/22 Newlands Shopping Centre", "31/07/1971", 16264);
	    
	@Before
	public void setup() {
		StoresDataService storesDataService = new StoresDataService() {
			@Override
			public List<StoreDTO> getStores() {
				List<StoreDTO> stores = new ArrayList<>();
				stores.add(STORE_LONDON);
				stores.add(STORE_BIRMINGHAM);
				stores.add(STORE_WITHAM);
				stores.add(STORE_SWANGE);
				stores.add(STORE_GLASGOW);
				return stores;
			}
		};
		
		facade = new StoreFacade(storesDataService);
	}
	
	@Test
	public void getStoresCitySortAsc() {
		List<StoreDTO> stores = facade.getAllStores("city");
		
		//check size
		assertEquals(5, stores.size());
		
		//check order
		assertEquals(STORE_BIRMINGHAM, stores.get(0));
		assertEquals(STORE_GLASGOW, stores.get(1));
		assertEquals(STORE_LONDON, stores.get(2));
		assertEquals(STORE_SWANGE, stores.get(3));
		assertEquals(STORE_WITHAM, stores.get(4));
	}
	
	@Test
	public void getStoresCitySortDesc() {
		List<StoreDTO> stores = facade.getAllStores("-city");
		
		//check size
		assertEquals(5, stores.size());
		
		//check order
		assertEquals(STORE_BIRMINGHAM, stores.get(4));
		assertEquals(STORE_GLASGOW, stores.get(3));
		assertEquals(STORE_LONDON, stores.get(2));
		assertEquals(STORE_SWANGE, stores.get(1));
		assertEquals(STORE_WITHAM, stores.get(0));
	}
	
	@Test
	public void getStoresDateSortAsc() {
		List<StoreDTO> stores = facade.getAllStores("date");
		
		//check size
		assertEquals(5, stores.size());
		
		//check order
		assertEquals(STORE_BIRMINGHAM, stores.get(0));
		assertEquals(STORE_SWANGE, stores.get(1));
		assertEquals(STORE_GLASGOW, stores.get(2));
		assertEquals(STORE_WITHAM, stores.get(3));
		assertEquals(STORE_LONDON, stores.get(4));
	}
	
	@Test
	public void getStoresDateSortDesc() {
		List<StoreDTO> stores = facade.getAllStores("-date");
		
		//check size
		assertEquals(5, stores.size());
		
		//check order
		assertEquals(STORE_BIRMINGHAM, stores.get(4));
		assertEquals(STORE_SWANGE, stores.get(3));
		assertEquals(STORE_GLASGOW, stores.get(2));
		assertEquals(STORE_WITHAM, stores.get(1));
		assertEquals(STORE_LONDON, stores.get(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getStoresNullSort() {
		facade.getAllStores(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getStoresInvalidSort() {
		facade.getAllStores("invalid");
	}
	
	@Test
	public void getStoreValidId() {
		assertEquals(STORE_LONDON, facade.getStore(STORE_LONDON.getStoreId()));
		assertEquals(STORE_WITHAM, facade.getStore(STORE_WITHAM.getStoreId()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getStoreNullId() {
		facade.getStore(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getStoreInvalidId() {
		facade.getStore("Invalid_ID");
	}
}
