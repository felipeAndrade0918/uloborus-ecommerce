package com.batutapps.uloborusecommerce.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.domain.ProductHistory;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {

	@Query(value = "SELECT h FROM ProductHistory h WHERE h.product = :product AND h.referenceDate = :referenceDate")
	ProductHistory findOneByProductAndReferenceDate(@Param("product") Product product, 
												 	@Param("referenceDate") LocalDateTime referenceDate);
}
