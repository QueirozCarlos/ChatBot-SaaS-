package com.sistema.chatbot.controller;

import com.sistema.chatbot.model.Report;
import com.sistema.chatbot.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public Optional<Report> getReportById(@PathVariable int id) {
        return reportService.getReportById(id);
    }

    @GetMapping("/products")
    public ResponseEntity<Resource> downloadProductsCsv() {
        try {
            String csvContent = reportService.generateProductsCsv();
            ByteArrayResource resource = new ByteArrayResource(csvContent.getBytes());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=products.csv")
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/sales")
    public ResponseEntity<Resource> downloadSalesCsv() {
        try {
            String csvContent = reportService.generateSalesCsv();
            ByteArrayResource resource = new ByteArrayResource(csvContent.getBytes());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sales.csv")
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.saveReport(report);
    }
}
