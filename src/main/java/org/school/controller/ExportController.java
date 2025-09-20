package org.school.controller;

import lombok.AllArgsConstructor;
import org.school.dto.ExportCriteria;
import org.school.service.ExportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/export")
@AllArgsConstructor
public class ExportController {
    private final ExportService exportService;

    @PostMapping("/students/xlsx")
    public ResponseEntity<byte[]> exportStudents(@RequestBody ExportCriteria criteria) throws IOException {
        byte[] excelData = exportService.exportStudentsToExcel(criteria);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelData);
    }

    @PostMapping("/teachers/xlsx")
    public ResponseEntity<byte[]> exportTeachers(@RequestBody ExportCriteria criteria) throws IOException {
        byte[] excelData = exportService.exportTeachersToExcel(criteria);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=teachers.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelData);
    }

}
