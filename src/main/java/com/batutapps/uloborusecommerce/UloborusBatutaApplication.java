package com.batutapps.uloborusecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(
  basePackageClasses = { UloborusBatutaApplication.class, Jsr310JpaConverters.class }
)
@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class UloborusBatutaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UloborusBatutaApplication.class, args);
	}
}
