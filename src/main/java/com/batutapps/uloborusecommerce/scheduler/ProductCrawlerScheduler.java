package com.batutapps.uloborusecommerce.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.batutapps.uloborusecommerce.service.ProductCrawler;

@Component
public class ProductCrawlerScheduler {

	private ProductCrawler productCrawler;
	
	@Autowired
	public ProductCrawlerScheduler(ProductCrawler productCrawler) {
		super();
		this.productCrawler = productCrawler;
	}

	@Scheduled(cron = "0 0 9 * * ?")
	public void schedule() {
		productCrawler.scheduledCrawl();
	}
}
