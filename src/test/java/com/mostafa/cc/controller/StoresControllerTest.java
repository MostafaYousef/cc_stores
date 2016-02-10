package com.mostafa.cc.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.mostafa.cc.CCStoresApplication;
import com.mostafa.cc.dto.StoreDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CCStoresApplication.class)
@WebIntegrationTest({ "server.port=9000" })
public class StoresControllerTest {
	private static final String HOST = "http://localhost:9000";
	private static final String STORE_URL = HOST + "/store";
	private static final String STORE_URL_WITH_SORT = STORE_URL + "?sort={sort}";
	private static final String STORE_URL_WITH_ID = STORE_URL + "/{id}";

	private RestTemplate template = new TestRestTemplate();

	@Test
	public void getAllStoresDefaultSort() {
		StoreDTO[] stores = template.getForObject(STORE_URL, StoreDTO[].class);

		// Check return size
		assertEquals(15, stores.length);

		// Check city order
		assertEquals("Aberdeen", stores[0].getCity());
		assertEquals("Birmingham", stores[2].getCity());
		assertEquals("Kings Heath", stores[10].getCity());
		assertEquals("Welwyn Garden City", stores[14].getCity());
	}

	@Test
	public void getAllStoresCitySortDesc() {
		StoreDTO[] stores = template.getForObject(STORE_URL_WITH_SORT, StoreDTO[].class, "-city");

		// Check return size
		assertEquals(15, stores.length);

		// Check city order
		assertEquals("Aberdeen", stores[14].getCity());
		assertEquals("Birmingham", stores[12].getCity());
		assertEquals("Kings Heath", stores[4].getCity());
		assertEquals("Welwyn Garden City", stores[0].getCity());
	}

	@Test
	public void getAllStoresDateSortAsc() {
		StoreDTO[] stores = template.getForObject(STORE_URL_WITH_SORT, StoreDTO[].class, "date");

		// Check return size
		assertEquals(15, stores.length);

		// Check city order
		assertEquals("Aberdeen", stores[13].getCity());
		assertEquals("Birmingham", stores[3].getCity());
		assertEquals("Kings Heath", stores[11].getCity());
		assertEquals("Welwyn Garden City", stores[7].getCity());
	}

	@Test
	public void getAllStoresDateSortDesc() {
		StoreDTO[] stores = template.getForObject(STORE_URL_WITH_SORT, StoreDTO[].class, "-date");

		// Check return size
		assertEquals(15, stores.length);

		// Check city order
		assertEquals("Aberdeen", stores[1].getCity());
		assertEquals("Birmingham", stores[11].getCity());
		assertEquals("Kings Heath", stores[3].getCity());
		assertEquals("Welwyn Garden City", stores[7].getCity());
	}

	@Test
	public void getAllStoresInvalidSort() {
		ResponseEntity<String> response = template.getForEntity(STORE_URL_WITH_SORT, String.class, "invalid");

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void getStore() {
		StoreDTO store = template.getForObject(STORE_URL_WITH_ID, StoreDTO.class,
				"8b30d554-866e-438a-b03b-56440302756e");

		assertEquals("8b30d554-866e-438a-b03b-56440302756e", store.getStoreId());
		assertEquals("Erdington", store.getCity());
		assertEquals("B23 6SA", store.getPostCode());
		assertEquals("125/131 High Street", store.getAddress());
		assertEquals("06/10/1952", store.getOpenedDate());
	}

	@Test
	public void getStoreInvalidId() {
		ResponseEntity<String> response = template.getForEntity(STORE_URL_WITH_ID, String.class, "invalid");

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
