package com.example.aybukearpaci.trendyol;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.aybukearpaci.trendyol.repositories")
public class TrendyolApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TrendyolApplication.class,args);
	}

	public TrendyolApplication()
	{
	}
	@Override
	public void run(String... args) throws Exception {
	}
}
