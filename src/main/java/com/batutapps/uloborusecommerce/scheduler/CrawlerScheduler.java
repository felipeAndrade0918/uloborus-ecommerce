package com.batutapps.uloborusecommerce.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.service.CrawlerService;
import com.batutapps.uloborusecommerce.service.ProductService;

@Component
public class CrawlerScheduler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private CrawlerService crawlerService;
	
	private ProductService productService;
	
	@Autowired
	public CrawlerScheduler(CrawlerService crawlerService, ProductService productService) {
		super();
		this.crawlerService = crawlerService;
		this.productService = productService;
	}

	@Scheduled(cron = "0 0/1 * * * ?")
	public void schedule() {
		logger.info("Listing products...");
		
		List<Product> products = productService.findAll();
		
		products.stream().forEach(p -> crawlerService.saveProduct(p.getUrl()));
		
		logger.info("Products updated!");
	}
}
