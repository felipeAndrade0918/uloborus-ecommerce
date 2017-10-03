package com.batutapps.uloborusecommerce.crawler.factory;

import com.batutapps.uloborusecommerce.crawler.Crawler;
import com.batutapps.uloborusecommerce.crawler.impl.DefaultB2wCrawler;
import com.batutapps.uloborusecommerce.enums.Ecommerce;

public class CrawlerFactory extends AbstractCrawlerFactory {

	@Override
	public Crawler getCrawler(Ecommerce ecommerce) {
		switch(ecommerce) {
			case AMERICANAS:
			case SUBMARINO:
				return new DefaultB2wCrawler(ecommerce);
			default:
				return null;
		}
	}

}
