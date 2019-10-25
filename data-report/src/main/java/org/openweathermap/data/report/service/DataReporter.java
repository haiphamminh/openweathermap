package org.openweathermap.data.report.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public interface DataReporter {
    byte[] generateReport(Timestamp startDate, Timestamp endDate, List<String> fields, String search)
            throws IOException, NoSuchFieldException, IllegalAccessException;
}
