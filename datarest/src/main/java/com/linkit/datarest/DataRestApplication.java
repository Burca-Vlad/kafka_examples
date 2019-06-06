package com.linkit.datarest;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class DataRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataRestApplication.class, args);
	}

	@Bean
	@Primary
	public FlywayMigrationStrategy getFlyway() {

		return new FlywayMigrationStrategy() {
			@Override
			public void migrate(Flyway flyway) {
				flyway.repair();
				flyway.migrate();
			}
		};
	}
}
