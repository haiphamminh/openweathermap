package org.openweathermap.data.report.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface DataReporter {
    byte[] generateReport(Timestamp startDate, Timestamp endDate, List<String> fields, String search, Pageable pageable)
            throws IOException, NoSuchFieldException, IllegalAccessException;
}
