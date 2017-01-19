package com.batutapps.uloborusecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.batutapps.uloborusecommerce.adapter.ProductAdapter;
import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.enums.Ecommerce;
import com.batutapps.uloborusecommerce.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Transactional
	public Product save(String productName, String url, Ecommerce ecommerce) {
		Product existingProduct = productRepository.findByUrl(url);
		
		if (existingProduct == null) {
			existingProduct = ProductAdapter.adapt(productName, url, ecommerce);
			productRepository.save(existingProduct);
		}
		
		return existingProduct;
	}
	
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return productRepository.findAll();
	}
}
