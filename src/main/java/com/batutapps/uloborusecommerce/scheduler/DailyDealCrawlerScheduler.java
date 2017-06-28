package com.batutapps.uloborusecommerce.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.batutapps.uloborusecommerce.service.DailyDealCrawler;

@Component
public class DailyDealCrawlerScheduler {

	private DailyDealCrawler dailyDealCrawler;
	
	@Autowired
	public DailyDealCrawlerScheduler(DailyDealCrawler dailyDealCrawler) {
		super();
		this.dailyDealCrawler = dailyDealCrawler;
	}

	@Scheduled(cron = "0 0 9 * * ?")
	public void schedule() {
		dailyDealCrawler.crawl();
	}
}
