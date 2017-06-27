package com.batutapps.uloborusecommerce.adapter;

import java.time.LocalDateTime;

import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.domain.ProductHistory;

public class ProductHistoryAdapter {

	public static ProductHistory adapt(Product product, String price) {
		ProductHistory history = new ProductHistory();
		history.setProduct(product);
		history.setPrice(price);
		history.setReferenceDate(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0));
		
		return history;
	}
}