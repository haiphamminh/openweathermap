package org.openweathermap.data.report.service.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.openweathermap.data.report.model.AggregatedData;
import org.openweathermap.data.report.model.Header;
import org.openweathermap.data.report.service.ExcelService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ExcelServiceImpl implements ExcelService {
    private static void createHeaderRow(Workbook wb, Sheet sheet, int rowIndex, List<String> headers) {
        CellStyle style = createCellStyle(wb, 10);
        Row row = sheet.createRow(rowIndex);
        for (int i = 0; i < headers.size(); i++) {
            createCell(row, Header.getDisplayName(headers.get(i)), i, style);
        }
    }

    private static CellStyle createCellStyle(Workbook wb, int fontHeight) {
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) fontHeight);
        font.setBold(true);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(font);
        return style;
    }

    private static void createCell(Row row, AggregatedData data, int colIndex,
                                   String headerName) throws NoSuchFieldException, IllegalAccessException {
        Field field = AggregatedData.class.getDeclaredField(headerName);
        field.setAccessible(true);
        Object value = field.get(data);
        if (value != null) {
            createCell(row, String.valueOf(value), colIndex);
        }
    }

    private static void createCell(Row row, String data, int colIndex) {
        createCell(row, data, colIndex, null);
    }

    private static void createCell(Row row, String data, int colIndex, CellStyle cellStyle) {
        Cell cell = row.createCell(colIndex);
        cell.setCellValue(data);
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
    }

    private static void createReportNameRow(Workbook wb, Sheet sheet, int rowIndex, int lastCol) {
        CellStyle style = createCellStyle(wb, 13);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, lastCol - 1));
        Row row = sheet.createRow(rowIndex);
        createCell(row, "Weather Data Report", 0, style);
    }

    private static List<String> extractHeaders(List<String> fields) {
        if (CollectionUtils.isEmpty(fields)) {
            return Header.getHeaderKeys();
        }
        List<String> headers = new ArrayList<>(fields);
        headers.add(0, Header.NO.getKey());
        return headers;
    }

    @Override
    public byte[] export(List<AggregatedData> data, List<String> fields) throws IOException, NoSuchFieldException,
            IllegalAccessException {
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("Weather Data");
        sheet.setAutobreaks(true);
        int rowIndex = 0;
        List<String> headers = extractHeaders(fields);
        createReportNameRow(wb, sheet, rowIndex++, headers.size());
        createHeaderRow(wb, sheet, rowIndex++, headers);

        int colIndex = 0;
        int no = 1;
        for (int i = rowIndex; i < data.size(); i++) {
            Row row = sheet.createRow(i);
            createCell(row, String.valueOf(no++), colIndex++);

            for (int j = 1; j < headers.size(); j++) {
                createCell(row, data.get(i), colIndex++, headers.get(j));
            }
            colIndex = 0;
        }
        for (colIndex = 0; colIndex < headers.size(); colIndex++) {
            sheet.autoSizeColumn(colIndex);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        wb.write(out);
        wb.close();
        return out.toByteArray();
    }
}
