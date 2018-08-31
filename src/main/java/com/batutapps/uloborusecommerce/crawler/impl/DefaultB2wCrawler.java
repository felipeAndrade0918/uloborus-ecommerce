package com.batutapps.uloborusecommerce.crawler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.batutapps.uloborusecommerce.crawler.Crawler;
import com.batutapps.uloborusecommerce.dto.ProductInfo;
import com.batutapps.uloborusecommerce.enums.Ecommerce;
import com.batutapps.uloborusecommerce.regex.DailyDealProductRegex;
import com.batutapps.uloborusecommerce.regex.PriceRegex;
import com.batutapps.uloborusecommerce.regex.ProductInfoRegex;

public class DefaultB2wCrawler implements Crawler {

	private Logger logger = Logger.getLogger(DefaultB2wCrawler.class);
	
	private Ecommerce ecommerce;
	
	public DefaultB2wCrawler(Ecommerce ecommerce) {
		super();
		this.ecommerce = ecommerce;
	}

	@Override
	public ProductInfo getProduct(String url) throws Exception {
		ProductInfo info = ProductInfoRegex.extract(url);
		
		if (info != null) {
			try {
				Document document = Jsoup.connect(info.getShortUrl())
										 .timeout(0)
										 .get();
				Elements productNameNode = document.select("h1.product-name");
				Elements salesPricesNode = document.select("p.sales-price");
				
				if (StringUtils.isNotBlank(productNameNode.text()) && StringUtils.isNotBlank(salesPricesNode.text())) {
					info.setName(productNameNode.text());
					info.setPrice(PriceRegex.extract(salesPricesNode.text()));
					
					return info;
				}
			} catch (Exception e) {
				logger.error(String.format("Error while getting the product %s", url), e);
				throw e;
			}
		}
		
		return null;
	}
	
	@Override
	public List<ProductInfo> getDailyDeals() throws Exception {
		List<ProductInfo> productsInfos = new ArrayList<>();
		
		try {
			Document document = Jsoup.connect(String.format("%s/oferta-do-dia", ecommerce.getUrl()))
									 .timeout(0)
									 .get();
			
			Elements products = document.select(".publishes .product-grid .grid-item");
			
			for (Element product : products) {
				String productUrl = product.select(".card-product-url").attr("href");
				
				if (productUrl != null) {
					String validUrl = DailyDealProductRegex.extract(productUrl);
					
					if (validUrl != null) {
						productsInfos.add(getProduct(ecommerce.getUrl() + validUrl));
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error while getting the daily deals", e);
			throw e;
		}
		
		return productsInfos.stream().filter(Objects::nonNull).collect(Collectors.toList());
	}
	
}
