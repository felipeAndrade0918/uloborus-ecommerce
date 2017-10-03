package com.batutapps.uloborusecommerce.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.batutapps.uloborusecommerce.dto.ProductInfo;
import com.batutapps.uloborusecommerce.enums.Ecommerce;

public class ProductInfoRegex {

	private static String PATTERN = "((?:http|https):\\/\\/www.([\\w]*).com.br\\/produto\\/[\\w]*)";
	
	private ProductInfoRegex() {
		
	}
	
	public static ProductInfo extract(String url) {
		ProductInfo info = null;
		
		Pattern pattern = Pattern.compile(PATTERN);
		
		Matcher matcher = pattern.matcher(url);
		
		if (matcher.find()) {
			info = new ProductInfo();
			info.setShortUrl(matcher.group(1));
			info.setEcommerce(Ecommerce.valueOf(matcher.group(2).toUpperCase()));
		}
		
		return info;
	}
}
