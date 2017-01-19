package com.batutapps.uloborusecommerce.regex;

import org.junit.Assert;
import org.junit.Test;

import com.batutapps.uloborusecommerce.dto.ProductInfo;
import com.batutapps.uloborusecommerce.enums.Ecommerce;
import com.batutapps.uloborusecommerce.regex.ProductInfoRegex;

public class ProductInfoRegexTest {

	@Test
	public void shouldExtractUrlAndEcommerceName() {
		String url = "http://www.submarino.com.br/produto/127115104/smartphone-motorola-moto-g"
				+ "-4-plus-dual-chip-android-6.0-tela-5.5-32gb-camera-16mp-preto?DCSext.recom=RR_"
				+ "home_page.rr1-TopSellers&condition=NEW&nm_origem=rec_home_page.rr1-TopSellers&n"
				+ "m_ranking_rec=3";
		
		String url2 = "http://www.americanas.com.br/produto/126077759/game-resident-evil-6-ps4";
		
		ProductInfo info = ProductInfoRegex.extract(url);
		ProductInfo info2 = ProductInfoRegex.extract(url2);
		
		Assert.assertEquals(Ecommerce.SUBMARINO, info.getEcommerce());
		Assert.assertEquals("http://www.submarino.com.br/produto/127115104", info.getShortUrl());
		
		Assert.assertEquals(Ecommerce.AMERICANAS, info2.getEcommerce());
		Assert.assertEquals("http://www.americanas.com.br/produto/126077759", info2.getShortUrl());
	}
}
