package com.aloranking.lfds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LfdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LfdsApplication.class, args);
	}

}
