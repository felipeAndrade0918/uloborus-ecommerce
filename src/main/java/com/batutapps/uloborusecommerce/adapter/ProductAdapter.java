package com.batutapps.uloborusecommerce.adapter;

import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.enums.Ecommerce;

public class ProductAdapter {

	public static Product adapt(String name, String url, Ecommerce ecommerce) {
		Product product = new Product();
		product.setName(name);
		product.setUrl(url);
		product.setEcommerce(ecommerce);
		
		return product;
	}
}
