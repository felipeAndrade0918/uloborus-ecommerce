package com.batutapps.uloborusecommerce.enums;

public enum Ecommerce {

	AMERICANAS("https://www.submarino.com.br"),
	SUBMARINO("http://www.americanas.com.br");
	
	private String url;
	
	private Ecommerce(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
}
