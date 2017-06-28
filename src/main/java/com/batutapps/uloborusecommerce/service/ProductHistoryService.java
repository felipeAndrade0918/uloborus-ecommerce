package com.batutapps.uloborusecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.batutapps.uloborusecommerce.adapter.ProductHistoryAdapter;
import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.domain.ProductHistory;
import com.batutapps.uloborusecommerce.repository.ProductHistoryRepository;

@Service
public class ProductHistoryService {

	private ProductHistoryRepository productHistoryRepository;
	
	@Autowired
	public ProductHistoryService(ProductHistoryRepository productHistoryRepository) {
		super();
		this.productHistoryRepository = productHistoryRepository;
	}

	@Transactional
	public void save(Product product, String price) {
		ProductHistory history = ProductHistoryAdapter.adapt(product, price);
		
		ProductHistory existingHistory = productHistoryRepository.findOneByProductAndReferenceDate(product, history.getReferenceDate());
		
		if (existingHistory == null) {
			productHistoryRepository.save(history);
		}
	}
	
	@Transactional(readOnly = true)
	public ProductHistory findOneByProductIdAndHistoryId(Long productId, Long historyId) {
		return productHistoryRepository.findOneByProductIdAndHistoryId(productId, historyId);
	}
}
