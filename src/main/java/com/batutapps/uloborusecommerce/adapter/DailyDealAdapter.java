package com.batutapps.uloborusecommerce.adapter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.batutapps.uloborusecommerce.domain.DailyDeal;
import com.batutapps.uloborusecommerce.domain.Product;

public class DailyDealAdapter {

	public static DailyDeal adapt(Long productId, BigDecimal price, LocalDateTime referenceDate) {
		DailyDeal dailyDeal = new DailyDeal();
		dailyDeal.setPrice(price);
		dailyDeal.setProduct(new Product(productId));
		dailyDeal.setReferenceDate(referenceDate);
		
		return dailyDeal;
	}
}
