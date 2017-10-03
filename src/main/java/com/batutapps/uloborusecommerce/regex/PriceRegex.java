package com.batutapps.uloborusecommerce.regex;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceRegex {

	private static final String PATTERN = "([\\d\\,\\.]*)$";
	
	private PriceRegex() {
		
	}
	
	public static BigDecimal extract(String price) {
		BigDecimal finalPrice = BigDecimal.ZERO;
		Pattern pattern = Pattern.compile(PATTERN);
		
		Matcher matcher = pattern.matcher(price);
		
		if (matcher.find()) {
			String rawPrice = matcher.group(1);
			finalPrice = new BigDecimal(rawPrice.replace(".", "").replace(",", "."));
		}
		
		return finalPrice;
	}
}
