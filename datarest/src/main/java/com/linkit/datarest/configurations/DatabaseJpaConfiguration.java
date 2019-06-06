package com.linkit.datarest.configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.linkit.datarest.models")
@EnableJpaRepositories("com.linkit.datarest.repository")
public class DatabaseJpaConfiguration {
}
