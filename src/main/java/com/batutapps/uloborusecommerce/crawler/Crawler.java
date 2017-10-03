package com.batutapps.uloborusecommerce.crawler;

import java.util.List;

import com.batutapps.uloborusecommerce.dto.ProductInfo;

public interface Crawler {

	ProductInfo getProduct(String url);
	
	List<String> getDailyDeals();
}
