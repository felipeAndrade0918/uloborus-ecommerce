package com.batutapps.uloborusecommerce.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DailyDeal extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private String price;
	
	private LocalDateTime referenceDate;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public LocalDateTime getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(LocalDateTime referenceDate) {
		this.referenceDate = referenceDate;
	}
	
}
