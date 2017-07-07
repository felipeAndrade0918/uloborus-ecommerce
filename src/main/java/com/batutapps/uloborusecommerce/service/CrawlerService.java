package com.batutapps.uloborusecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.batutapps.uloborusecommerce.crawler.Crawler;
import com.batutapps.uloborusecommerce.crawler.factory.AbstractCrawlerFactory;
import com.batutapps.uloborusecommerce.crawler.factory.CrawlerFactory;
import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.dto.ProductInfo;
import com.batutapps.uloborusecommerce.enums.Ecommerce;
import com.batutapps.uloborusecommerce.regex.EcommerceRegex;

@Service
public class CrawlerService {

	private ProductService productService;
	
	private ProductHistoryService historyService;
	
	private DailyDealService dailyDealService;

	@Autowired
	public CrawlerService(ProductService productService, ProductHistoryService historyService,
			DailyDealService dailyDealService) {
		super();
		this.productService = productService;
		this.historyService = historyService;
		this.dailyDealService = dailyDealService;
	}

	@Transactional
	public Product saveProduct(String url) {
		Ecommerce ecommerce = EcommerceRegex.extract(url);
		
		if (ecommerce != null) {
			AbstractCrawlerFactory crawlerFactory = new CrawlerFactory();
			
			Crawler crawler = crawlerFactory.getCrawler(ecommerce);
			
			if (crawler != null) {
				ProductInfo productInfo = crawler.crawl(url);
				
				Product product = productService.save(productInfo);
				
				historyService.save(product, productInfo.getPrice());
				
				return productService.findOneJoinHistory(product.getId());
			}
		}
		
		return null;
	}
	
	public void saveDailyDeals(Ecommerce ecommerce) {
		AbstractCrawlerFactory crawlerFactory = new CrawlerFactory();
		Crawler crawler = crawlerFactory.getCrawler(ecommerce);
		
		if (crawler != null) {
			List<String> urls = crawler.dailyDealCrawl(ecommerce);
			
			urls.stream().forEach(url -> {
				Product existingProduct = saveProduct(url);
				dailyDealService.save(existingProduct.getLatestPrice(), existingProduct.getId());
			});
		}
	}
	
}
