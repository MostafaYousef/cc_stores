package com.mostafa.cc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.mostafa.cc.dto.StoreDTO;

@Component
public class FetchStoresDataService implements StoresDataService {
	private static final String SEPARATOR = "\"?(,|$)(?=(([^\"]*\"){2})*[^\"]*$) *\"?";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	@Value("${data.stores.url}")
	private String dataUrl;

	@Override
	@Cacheable("stores")
	// FIXME: Bad practice to use the presentation DTO.
	public List<StoreDTO> getStores() {
		List<StoreDTO> stores = new ArrayList<>();
		try {
			URL url = new URL(dataUrl);
			InputStream inputStream = url.openStream();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				// skip first line
				String line = reader.readLine();

				while ((line = reader.readLine()) != null) {
					String[] storeLine = line.split(SEPARATOR);
					stores.add(new StoreDTO(storeLine[0], storeLine[1], storeLine[2], storeLine[3], storeLine[4],
							getDaysSinceDate(storeLine[4])));
				}
			} finally {
				inputStream.close();
			}
		} catch (IOException e) {

		}

		return stores;
	}

	private int getDaysSinceDate(String dateString) {
		try {
			Date date = DATE_FORMAT.parse(dateString);
			int days = (int) ((System.currentTimeMillis() - date.getTime()) / (1000 * 3600 * 24));
			return days;
		} catch (ParseException e) {
			return -1;
		}
	}
}
