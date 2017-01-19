package com.batutapps.uloborusecommerce.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.batutapps.uloborusecommerce.service.CrawlerService;

@Component
public class CrawlerScheduler {

	private CrawlerService crawlerService;
	
	@Autowired
	public CrawlerScheduler(CrawlerService crawlerService) {
		super();
		this.crawlerService = crawlerService;
	}

	@Scheduled(cron = "0 0 9 * * ?")
	public void schedule() {
		crawlerService.scheduledCrawl();
	}
}
