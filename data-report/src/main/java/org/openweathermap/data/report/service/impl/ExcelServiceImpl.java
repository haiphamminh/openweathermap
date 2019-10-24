package org.openweathermap.data.report.service.impl;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openweathermap.data.report.model.AggregatedData;
import org.openweathermap.data.report.service.ExcelService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExcelServiceImpl implements ExcelService {
    private static void createHeaderRow(Row headerRow, List<String> headers) {
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
        }
    }

    private static void createCell(Row row, AggregatedData data, int colIndex,
                                   String headerName) throws NoSuchFieldException, IllegalAccessException {
        Field field = AggregatedData.class.getDeclaredField(headerName);
        field.setAccessible(true);
        Object value = field.get(data);
        if (value != null) {
            Cell cell = row.createCell(colIndex);
            cell.setCellValue(String.valueOf(value));
        }
    }

    private static void createCell(Row row, int data, int colIndex) {
        Cell cell = row.createCell(colIndex);
        cell.setCellValue(data);
    }

    private static List<String> extractHeaders() {
        Field[] fields = AggregatedData.class.getDeclaredFields();
        List<String> headers = Stream.of(fields)
                                     .map(Field::getName)
                                     .collect(Collectors.toList());
        headers.add(0, "No");
        return headers;
    }

    @Override
    public byte[] export(String excelFile,
                         List<AggregatedData> data) throws IOException, NoSuchFieldException, IllegalAccessException {
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("Weather Data");
        List<String> headers = extractHeaders();
        createHeaderRow(sheet.createRow(0), headers);

        int colIndex = 0;
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            createCell(row, i + 1, colIndex++);

            for (int j = 1; j < headers.size(); j++) {
                createCell(row, data.get(i), colIndex++, headers.get(j));
            }
            colIndex = 0;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        wb.write(out);
        wb.close();
        return out.toByteArray();
    }
}
