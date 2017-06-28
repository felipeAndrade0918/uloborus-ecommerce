package com.batutapps.uloborusecommerce.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.dto.ProductInfo;
import com.batutapps.uloborusecommerce.regex.ProductInfoRegex;

@Component
public class ProductCrawler {

	private Logger logger = Logger.getLogger(getClass());
	
	private ProductService productService;
	
	private ProductHistoryService productHistoryService;
	
	@Autowired
	public ProductCrawler(ProductService productService, ProductHistoryService productHistoryService) {
		super();
		this.productService = productService;
		this.productHistoryService = productHistoryService;
	}

	public void scheduledCrawl() {
		logger.info("Collecting existing products...");
		List<Product> products = productService.findAll();
		
		logger.info("Preparing to crawl!");
		products.parallelStream().forEach(p -> crawl(p.getUrl()));
		logger.info("Crawled!!");
	}
	
	public Product crawl(String url) {
		ProductInfo info = ProductInfoRegex.extract(url);
		
		if (info != null) {
			try {
				Document document = Jsoup.connect(info.getShortUrl()).get();
				Elements productNameNode = document.select("h1.product-name");
				Elements salesPricesNode = document.select(".sales-price");
				
				if (productNameNode != null && salesPricesNode != null) {
					Product product = productService.save(productNameNode.text(), info.getShortUrl(), info.getEcommerce());
					
					productHistoryService.save(product, salesPricesNode.text());
					
					return productService.findOneJoinHistory(product.getId());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
