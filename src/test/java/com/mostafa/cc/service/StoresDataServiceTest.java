package com.mostafa.cc.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mostafa.cc.CCStoresApplication;
import com.mostafa.cc.dto.StoreDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CCStoresApplication.class)
@WebIntegrationTest({ "server.port=9000" })
public class StoresDataServiceTest {
	@Autowired
	private StoresDataService storesDataService;

	@Test
	public void test() throws InterruptedException, ParseException {
		List<StoreDTO> stores = storesDataService.getStores();
		assertEquals(15, stores.size());

		StoreDTO store = stores.get(0);
		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f", store.getStoreId());
		assertEquals("AB11 5PS", store.getPostCode());
		assertEquals("Aberdeen", store.getCity());
		assertEquals("LSU 1A Union Square, Guild Street", store.getAddress());
		assertEquals("21/02/1965", store.getOpenedDate());
		assertEquals(calculateDaysOpened(store), store.getDaysOpened());
	}

	private int calculateDaysOpened(StoreDTO store) throws ParseException {
		long timeWhenOpened = StoresDataService.DATE_FORMAT.parse(store.getOpenedDate()).getTime();
		return (int) TimeUnit.DAYS.convert(System.currentTimeMillis() - timeWhenOpened, TimeUnit.MILLISECONDS);
	}
}
