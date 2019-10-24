package org.openweathermap.data.etl.config;

import org.openweathermap.data.repo.config.RepoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RepoConfiguration.class)
public class AutoConfiguration {
}
