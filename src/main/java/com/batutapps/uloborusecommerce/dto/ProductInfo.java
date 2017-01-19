package com.batutapps.uloborusecommerce.dto;

import com.batutapps.uloborusecommerce.enums.Ecommerce;

public class ProductInfo {

	private String shortUrl;
	
	private Ecommerce ecommerce;

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
	
}
