package com.batutapps.uloborusecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.service.CrawlerService;
import com.batutapps.uloborusecommerce.service.ProductService;

@RestController
public class CrawlerController {

	private CrawlerService crawlService;
	
	private ProductService productService;
	
	@Autowired
	public CrawlerController(CrawlerService crawlService, ProductService productService) {
		super();
		this.crawlService = crawlService;
		this.productService = productService;
	}

	@Transactional
	@RequestMapping("/add")
	public void addProduct(@RequestParam(name = "url") String url) {
		crawlService.crawl(url);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> listProducts() {
		return productService.findAll();
	}
}
