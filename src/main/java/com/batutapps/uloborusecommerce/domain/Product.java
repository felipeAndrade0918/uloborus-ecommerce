package com.batutapps.uloborusecommerce.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.batutapps.uloborusecommerce.enums.Ecommerce;

@Entity
public class Product extends AbstractEntity {

	private String name;
	
	private String url;
	
	@Enumerated(EnumType.STRING)
	private Ecommerce ecommerce;
	
	@OneToMany(mappedBy = "product")
	private List<ProductHistory> history;

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
	
}
