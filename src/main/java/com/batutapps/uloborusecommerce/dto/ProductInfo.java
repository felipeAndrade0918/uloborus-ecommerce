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
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ecommerce == null) ? 0 : ecommerce.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((shortUrl == null) ? 0 : shortUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInfo other = (ProductInfo) obj;
		if (ecommerce != other.ecommerce)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (shortUrl == null) {
			if (other.shortUrl != null)
				return false;
		} else if (!shortUrl.equals(other.shortUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductInfo [name=" + name + ", shortUrl=" + shortUrl + ", ecommerce=" + ecommerce + ", price=" + price
				+ "]";
	}
	
}
