package com.batutapps.uloborusecommerce.crawler.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.batutapps.uloborusecommerce.crawler.Crawler;
import com.batutapps.uloborusecommerce.dto.ProductInfo;
import com.batutapps.uloborusecommerce.enums.Ecommerce;
import com.batutapps.uloborusecommerce.regex.DailyDealProductRegex;
import com.batutapps.uloborusecommerce.regex.ProductInfoRegex;

@Component
public class DefaultB2wCrawler implements Crawler {

	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public ProductInfo crawl(String url) {
		ProductInfo info = ProductInfoRegex.extract(url);
		
		if (info != null) {
			try {
				Document document = Jsoup.connect(info.getShortUrl()).get();
				Elements productNameNode = document.select("h1.product-name");
				Elements salesPricesNode = document.select(".sales-price");
				
				if (productNameNode != null && salesPricesNode != null) {
					info.setName(productNameNode.text());
					info.setPrice(salesPricesNode.text());
					
					return info;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public List<String> dailyDealCrawl(Ecommerce ecommerce) {
		List<String> infos = new ArrayList<>();
		
		try {
			Document document = Jsoup.connect(String.format("%s/oferta-do-dia", ecommerce.getUrl())).get();
			
			Elements products = document.select(".publishes .product-grid .grid-item");
			
			for (Element product : products) {
				String productUrl = product.select(".card-product-url").attr("href");
				
				if (productUrl != null) {
					String validUrl = DailyDealProductRegex.extract(productUrl);
					
					if (validUrl != null) {
						infos.add(ecommerce.getUrl() + validUrl);
					}
				}
			}
		} catch (IOException e) {
			logger.error("Error while getting the daily deals", e);
		}
		
		return infos;
	}
}
