package com.batutapps.uloborusecommerce.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.batutapps.uloborusecommerce.util.jsonview.ProductWithHistoryView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class ProductHistory extends AbstractEntity {

	@JsonView(value = {ProductWithHistoryView.class})
	private String price;

	@JsonView(value = {ProductWithHistoryView.class})
	private LocalDateTime referenceDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
