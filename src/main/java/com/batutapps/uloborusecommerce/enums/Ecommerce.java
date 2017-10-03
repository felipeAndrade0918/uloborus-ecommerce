package com.batutapps.uloborusecommerce.enums;

public enum Ecommerce {

	AMERICANAS("http://www.americanas.com.br"),
	SUBMARINO("https://www.submarino.com.br");
	
	private String url;
	
	private Ecommerce(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
}
