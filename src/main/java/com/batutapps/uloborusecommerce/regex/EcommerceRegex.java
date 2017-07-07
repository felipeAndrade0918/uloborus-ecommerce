package com.batutapps.uloborusecommerce.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.batutapps.uloborusecommerce.enums.Ecommerce;

public class EcommerceRegex {

	private static String PATTERN = "(?:http|https):\\/\\/www.([\\w]*).com.br";
	
	public static Ecommerce extract(String url) {
		Ecommerce ecommerce = null;
		
		Pattern pattern = Pattern.compile(PATTERN);
		
		Matcher matcher = pattern.matcher(url);
		
		if (matcher.find()) {
			ecommerce = Ecommerce.valueOf(matcher.group(1).toUpperCase());
		}
		
		return ecommerce;
	}
}
