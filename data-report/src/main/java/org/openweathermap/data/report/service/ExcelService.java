package org.openweathermap.data.report.service;

import org.openweathermap.data.report.model.AggregatedData;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    byte[] export(String excelFile,
                  List<AggregatedData> data) throws IOException, NoSuchFieldException, IllegalAccessException;
}
