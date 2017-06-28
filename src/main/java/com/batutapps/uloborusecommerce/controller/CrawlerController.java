package com.batutapps.uloborusecommerce.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.service.CrawlerService;
import com.batutapps.uloborusecommerce.service.ProductService;
import com.batutapps.uloborusecommerce.util.jsonview.ProductView;
import com.batutapps.uloborusecommerce.util.jsonview.ProductWithHistoryView;
import com.fasterxml.jackson.annotation.JsonView;

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
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addProduct(@RequestParam(name = "url") String url) throws URISyntaxException {
		Long productId = crawlService.crawl(url);
		
		if (productId != null) {
			return ResponseEntity.created(new URI("/product/" + productId)).build();
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> listProducts() {
		return productService.findAll();
	}
	
	@JsonView(value = {ProductView.class})
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable Long productId) {
		return productService.findOneJoinHistory(productId);
	}
	
	@JsonView(value = {ProductWithHistoryView.class})
	@RequestMapping(value = "/product/{productId}/history", method = RequestMethod.GET)
	public Product getProductWithHistory(@PathVariable Long productId) {
		return productService.findOneJoinHistory(productId);
	}
}
