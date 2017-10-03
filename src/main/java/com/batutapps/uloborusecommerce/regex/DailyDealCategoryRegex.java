package com.batutapps.uloborusecommerce.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DailyDealCategoryRegex {

	private static String PATTERN = "(\\/categoria\\/[\\w]*)";
	
	private DailyDealCategoryRegex() {
		
	}
	
	public static String extract(String url) {
		String validUrl = null;
		
		Pattern pattern = Pattern.compile(PATTERN);
		
		Matcher matcher = pattern.matcher(url);
		
		if (matcher.find()) {
			validUrl = matcher.group(1);
		}
		
		return validUrl;
	}
}
