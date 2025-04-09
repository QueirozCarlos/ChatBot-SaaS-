package com.sistema.chatbot.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Map;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reportType;
//    @ElementCollection
//    private Map<String, Object> filters;
    private Date generatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

//    public Map<String, Object> getFilters() {
//        return filters;
//    }

//    public void setFilters(Map<String, Object> filters) {
//        this.filters = filters;
//    }

    public Date getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Date generatedAt) {
        this.generatedAt = generatedAt;
    }
}
