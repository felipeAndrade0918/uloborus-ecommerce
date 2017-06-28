package com.batutapps.uloborusecommerce.service;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.enums.Ecommerce;
import com.batutapps.uloborusecommerce.regex.DailyDealProductRegex;

@Component
public class DailyDealCrawler {

	private Logger logger = Logger.getLogger(getClass());
	
	private ProductCrawler productCrawler;
	
	private DailyDealService dailyDealService;
	
	@Autowired
	public DailyDealCrawler(ProductCrawler productCrawler, DailyDealService dailyDealService) {
		super();
		this.productCrawler = productCrawler;
		this.dailyDealService = dailyDealService;
	}

	public void crawl() {
		for (Ecommerce ecommerce : Ecommerce.values()) {
			try {
				Document document = Jsoup.connect(String.format("%s/oferta-do-dia", ecommerce.getUrl())).get();
				
				Elements products = document.select(".spacey-lista .product-grid .grid-item");
				
				for (Element product : products) {
					System.out.println(product.select(".card-product-url").attr("href"));
					
					String productUrl = product.select(".card-product-url").attr("href");
					
					if (productUrl != null) {
						String validUrl = DailyDealProductRegex.extract(productUrl);
						
						if (validUrl != null) {
							Product existingProduct = productCrawler.crawl(ecommerce.getUrl() + validUrl);
							dailyDealService.save(existingProduct.getLatestPrice(), existingProduct.getId());
						}
					}
				}
			} catch (IOException e) {
				logger.error("Error while getting the daily deals", e);
			}
		}
	}
}
