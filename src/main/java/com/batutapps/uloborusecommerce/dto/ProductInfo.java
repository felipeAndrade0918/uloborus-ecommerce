package com.batutapps.uloborusecommerce.dto;

import com.batutapps.uloborusecommerce.enums.Ecommerce;

public class ProductInfo {

	private String name;
	
	private String shortUrl;
	
	private Ecommerce ecommerce;
	
	private String price;

	public ProductInfo() {
		super();
	}

	public ProductInfo(String name, String shortUrl, Ecommerce ecommerce, String price) {
		super();
		this.name = name;
		this.shortUrl = shortUrl;
		this.ecommerce = ecommerce;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public Ecommerce getEcommerce() {
		return ecommerce;
	}

	public void setEcommerce(Ecommerce ecommerce) {
		this.ecommerce = ecommerce;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}
