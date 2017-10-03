package com.batutapps.uloborusecommerce.domain;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.batutapps.uloborusecommerce.enums.Ecommerce;
import com.batutapps.uloborusecommerce.util.jsonview.ProductView;
import com.batutapps.uloborusecommerce.util.jsonview.ProductWithHistoryView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Product extends AbstractEntity {

	@JsonView(value = {ProductView.class, ProductWithHistoryView.class})
	private String name;
	
	@JsonView(value = {ProductView.class, ProductWithHistoryView.class})
	private String url;
	
	@JsonView(value = {ProductView.class, ProductWithHistoryView.class})
	@Enumerated(EnumType.STRING)
	private Ecommerce ecommerce;
	
	@JsonView(value = {ProductWithHistoryView.class})
	@OneToMany(mappedBy = "product")
	private List<ProductHistory> history;

	public Product() {
		super();
	}
	
	public Product(Long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Ecommerce getEcommerce() {
		return ecommerce;
	}

	public void setEcommerce(Ecommerce ecommerce) {
		this.ecommerce = ecommerce;
	}

	public List<ProductHistory> getHistory() {
		return history;
	}

	public void setHistory(List<ProductHistory> history) {
		this.history = history;
	}
	
	public BigDecimal getLatestPrice() {
		ProductHistory productHistory = history.stream().max(Comparator.comparing(ProductHistory::getId)).orElse(null);
		
		if (productHistory != null) {
			return productHistory.getPrice();
		}
		
		return null;
	}
	
}
