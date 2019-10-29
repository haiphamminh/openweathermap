package org.openweathermap.data.repo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "centralDateTimeProvider")
@EnableJpaRepositories({"org.openweathermap.data.repo"})
@EntityScan("org.openweathermap.data.repo.domain")
public class RepoConfiguration {
    @Bean
    public CentralDateTimeProvider centralDateTimeProvider() {
        return new CentralDateTimeProvider();
    }

    @Bean
    public AuditorAwareImpl auditorAware() {
        return new AuditorAwareImpl();
    }
}
