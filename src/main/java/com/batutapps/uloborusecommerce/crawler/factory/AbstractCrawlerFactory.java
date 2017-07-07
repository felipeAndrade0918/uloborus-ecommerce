package com.batutapps.uloborusecommerce.crawler.factory;

import com.batutapps.uloborusecommerce.crawler.Crawler;
import com.batutapps.uloborusecommerce.enums.Ecommerce;

public abstract class AbstractCrawlerFactory {

	public abstract Crawler getCrawler(Ecommerce ecommerce);
	
}
