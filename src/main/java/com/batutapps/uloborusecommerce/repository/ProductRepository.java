package com.batutapps.uloborusecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.batutapps.uloborusecommerce.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT p FROM Product p WHERE p.url = :url")
	Product findByUrl(@Param(value = "url") String url);
}
