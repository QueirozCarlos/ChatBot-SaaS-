package com.sistema.chatbot.repository;

import com.sistema.chatbot.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
