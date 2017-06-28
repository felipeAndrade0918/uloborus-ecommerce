package com.batutapps.uloborusecommerce.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.domain.ProductHistory;
import com.batutapps.uloborusecommerce.service.CrawlerService;
import com.batutapps.uloborusecommerce.service.ProductHistoryService;
import com.batutapps.uloborusecommerce.service.ProductService;
import com.batutapps.uloborusecommerce.util.jsonview.ProductView;
import com.batutapps.uloborusecommerce.util.jsonview.ProductWithHistoryView;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class CrawlerController {

	private CrawlerService crawlService;
	
	private ProductService productService;
	
	private ProductHistoryService historyService;
	
	@Autowired
	public CrawlerController(CrawlerService crawlService, ProductService productService,
			ProductHistoryService historyService) {
		super();
		this.crawlService = crawlService;
		this.productService = productService;
		this.historyService = historyService;
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
	public Page<Product> listProducts(@PageableDefault Pageable pageable) {
		return productService.findAll(pageable);
	}
	
	@JsonView(value = {ProductView.class})
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(@PathVariable Long productId) {
		Product product = productService.findOneJoinHistory(productId);
		
		if (product == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(product);
		}
	}
	
	@JsonView(value = {ProductWithHistoryView.class})
	@RequestMapping(value = "/product/{productId}/history", method = RequestMethod.GET)
	public ResponseEntity<?> getProductWithHistory(@PathVariable Long productId) {
		Product product = productService.findOneJoinHistory(productId);
		
		if (product == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(product);
		}
	}
	
	@RequestMapping(value = "/product/{productId}/history/{historyId}", method = RequestMethod.GET)
	public ResponseEntity<?> getHistory(@PathVariable Long productId, @PathVariable Long historyId) {
		ProductHistory history = historyService.findOneByProductIdAndHistoryId(productId, historyId);
		
		if (history == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(history);
		}
	}
}
