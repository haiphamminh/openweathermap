package org.openweathermap.data.report.controller;

import static java.time.LocalDateTime.now;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.openweathermap.data.report.model.Fields;
import org.openweathermap.data.report.service.DataReporter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class DataReporterController {
    private final DataReporter reporter;

    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> generateReport(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate,
            @RequestParam(required = false, name = "q") String search,
            @RequestParam(required = false) List<String> fields,
            @PageableDefault(size = 50, sort = {"id"}) Pageable pageable)
            throws IOException, NoSuchFieldException, IllegalAccessException {
        byte[] data = reporter.generateReport(startDate, endDate, fields, search, pageable);
        String excelFile = String.format("Report-%s.xls", now());
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + excelFile)
                             .body(data);
    }

    @GetMapping("/fields")
    public ResponseEntity<Map<String, String>> getSupportedFields() {
        Map<String, String> supportedFields = Stream.of(Fields.values())
                                                    .filter(f -> !f.getKey()
                                                                   .equalsIgnoreCase(Fields.NO.getKey()))
                                                    .collect(Collectors.toMap(Fields::getKey, Fields::getDisplayName));
        return ResponseEntity.ok(supportedFields);
    }
}
