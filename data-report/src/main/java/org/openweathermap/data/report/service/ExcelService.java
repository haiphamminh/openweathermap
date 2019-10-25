package org.openweathermap.data.report.service;

import java.io.IOException;
import java.util.List;
import org.openweathermap.data.report.model.AggregatedData;

public interface ExcelService {
    byte[] export(List<AggregatedData> data, List<String> fields)
            throws IOException, NoSuchFieldException, IllegalAccessException;
}
