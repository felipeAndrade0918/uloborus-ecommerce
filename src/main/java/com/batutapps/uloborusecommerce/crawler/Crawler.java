package com.batutapps.uloborusecommerce.crawler;

import java.util.List;

import com.batutapps.uloborusecommerce.dto.ProductInfo;
import com.batutapps.uloborusecommerce.enums.Ecommerce;

public interface Crawler {

	ProductInfo crawl(String url);
	
	List<String> dailyDealCrawl(Ecommerce ecommerce);
}
