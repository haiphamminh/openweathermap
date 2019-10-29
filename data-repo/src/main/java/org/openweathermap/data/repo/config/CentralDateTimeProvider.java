package org.openweathermap.data.repo.config;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;
import org.springframework.data.auditing.DateTimeProvider;

public class CentralDateTimeProvider implements DateTimeProvider {
    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(LocalDateTime.now()
                                        .atZone(ZoneOffset.UTC)
                                        .toInstant());
    }
}
