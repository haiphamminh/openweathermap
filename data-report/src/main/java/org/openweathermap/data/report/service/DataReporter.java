package org.openweathermap.data.report.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface DataReporter {
    byte[] generateReport(LocalDateTime startDate, LocalDateTime endDate, List<String> fields, String search,
                          Pageable pageable)
            throws IOException, NoSuchFieldException, IllegalAccessException;
}
