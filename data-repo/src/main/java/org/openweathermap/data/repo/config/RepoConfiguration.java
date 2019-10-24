package org.openweathermap.data.repo.config;

import org.openweathermap.data.repo.impl.BaseRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"org.openweathermap.data.repo"}, repositoryBaseClass = BaseRepositoryImpl.class)
@EntityScan("org.openweathermap.data.repo.domain")
public class RepoConfiguration {
}
