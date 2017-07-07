package com.batutapps.uloborusecommerce.adapter;

import com.batutapps.uloborusecommerce.domain.Product;
import com.batutapps.uloborusecommerce.dto.ProductInfo;

public class ProductAdapter {

	public static Product adapt(ProductInfo productInfo) {
		Product product = new Product();
		product.setName(productInfo.getName());
		product.setUrl(productInfo.getShortUrl());
		product.setEcommerce(productInfo.getEcommerce());
		
		return product;
	}
}
