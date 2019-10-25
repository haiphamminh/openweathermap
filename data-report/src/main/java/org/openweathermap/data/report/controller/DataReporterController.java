package org.openweathermap.data.report.controller;

import static java.time.LocalDateTime.now;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openweathermap.data.report.model.Header;
import org.openweathermap.data.report.service.DataReporter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DataReporterController {
    private final DataReporter reporter;

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> generateReport(@RequestParam(required = false) Timestamp startDate,
                                                 @RequestParam(required = false) Timestamp endDate,
                                                 @RequestParam(required = false) String search,
                                                 @RequestParam(required = false) List<String> fields)
            throws IOException, NoSuchFieldException, IllegalAccessException {
        byte[] data = reporter.generateReport(startDate, endDate, fields, search);
        String excelFile = String.format("report-%s.xls", now());
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + excelFile)
                             .body(data);
    }

    @GetMapping("/report/fields")
    public ResponseEntity<List<String>> getSupportedFields() {
        List<String> supportedFields = Header.getHeaderKeys();
        supportedFields.removeIf(f -> f.equalsIgnoreCase(Header.NO.getKey()));
        return ResponseEntity.ok(supportedFields);
    }
}
