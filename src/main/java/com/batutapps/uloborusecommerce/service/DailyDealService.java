package com.batutapps.uloborusecommerce.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.batutapps.uloborusecommerce.adapter.DailyDealAdapter;
import com.batutapps.uloborusecommerce.domain.DailyDeal;
import com.batutapps.uloborusecommerce.repository.DailyDealRepository;

@Service
public class DailyDealService {

	private DailyDealRepository dailyDealRepository;
	
	@Autowired
	public DailyDealService(DailyDealRepository dailyDealRepository) {
		super();
		this.dailyDealRepository = dailyDealRepository;
	}

	@Transactional
	public DailyDeal save(String price, Long productId) {
		LocalDateTime referenceDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
		
		DailyDeal existingDeal = dailyDealRepository.findOneByProductIdAndReferenceDate(productId, referenceDate);
		
		if (existingDeal == null) {
			existingDeal = DailyDealAdapter.adapt(productId, price, referenceDate);
			dailyDealRepository.save(existingDeal);
		}
		
		return existingDeal;
	}
}
