package org.openweathermap.data.repo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories({"org.openweathermap.data.repo"})
@EntityScan("org.openweathermap.data.repo.domain")
public class RepoConfiguration {
}
