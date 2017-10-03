package com.batutapps.uloborusecommerce.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.batutapps.uloborusecommerce.enums.Ecommerce;
import com.batutapps.uloborusecommerce.service.CrawlerService;

@Component
public class DailyDealCrawlerScheduler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private CrawlerService crawlerService;
	
	@Autowired
	public DailyDealCrawlerScheduler(CrawlerService crawlerService) {
		super();
		this.crawlerService = crawlerService;
	}

	@Scheduled(cron = "0 0 9 * * ?")
	public void schedule() {
		logger.info("Get ready to scan the daily deals!");
		
		for (Ecommerce ecommerce : Ecommerce.values()) {
			logger.info(String.format("Scanning daily deals for %s", ecommerce));
			crawlerService.saveDailyDeals(ecommerce);
			logger.info(String.format("Daily deals for %s DONE!", ecommerce));
		}
		
		logger.info("All daily deals DONE!");
	}
}
