package org.openweathermap.data.report.controller;

import lombok.RequiredArgsConstructor;
import org.openweathermap.data.report.service.DataReporter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;

import static java.time.LocalDateTime.now;

@RestController
@RequiredArgsConstructor
public class DataReporterController {
    private final DataReporter reporter;

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> generateReport(@RequestParam(required = false) Timestamp startDate,
                                                 @RequestParam(required = false) Timestamp endDate,
                                                 @RequestParam(required = false)
                                                         String search) throws IOException, NoSuchFieldException,
            IllegalAccessException {
//        List<WeatherDataEntity> list = reporter.search();
        byte[] data = reporter.generate(null, Timestamp.valueOf("2019-10-18 10:00:00.0"));
        String excelFile = String.format("report-%s.xls", now());
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + excelFile)
                             .body(data);
    }
}
