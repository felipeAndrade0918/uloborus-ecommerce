package com.batutapps.uloborusecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.batutapps.uloborusecommerce.adapter.ProductAdapter;
import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.dto.ProductInfo;
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
	public Product save(ProductInfo productInfo) {
		Product existingProduct = productRepository.findByUrl(productInfo.getShortUrl());
		
		if (existingProduct == null) {
			existingProduct = ProductAdapter.adapt(productInfo);
			productRepository.save(existingProduct);
		}
		
		return existingProduct;
	}
	
	@Transactional(readOnly = true)
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Product findOne(Long productId) {
		return productRepository.findOne(productId);
	}
	
	@Transactional(readOnly = true)
	public Product findOneJoinHistory(Long productId) {
		return productRepository.findOneJoinHistory(productId);
	}
}
