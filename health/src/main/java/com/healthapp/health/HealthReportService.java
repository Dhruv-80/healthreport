package com.healthapp.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class HealthReportService {

    @Autowired
    private HealthReportRepository repository;

    public Page<HealthReport> getReportsWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Page<HealthReport> searchReports(String name, int page, int size) {
        return repository.searchByName(name, PageRequest.of(page, size));
    }

    public void saveReport(HealthReport report) {
        report.setTimestamp(java.time.LocalDateTime.now());
        repository.save(report);
    }
}
