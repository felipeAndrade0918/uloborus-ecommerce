package com.batutapps.uloborusecommerce.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.batutapps.uloborusecommerce.domain.DailyDeal;

public interface DailyDealRepository extends JpaRepository<DailyDeal, Long> {

	@Query(value = "SELECT d FROM DailyDeal d "
				+ " WHERE d.product.id = :productId "
				+ " AND d.referenceDate = :referenceDate")
	DailyDeal findOneByProductIdAndReferenceDate(@Param("productId") Long productId, 
														   @Param("referenceDate") LocalDateTime referenceDate);
}
