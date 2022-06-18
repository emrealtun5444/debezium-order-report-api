package com.trendyol.order.reporting.app.service;

import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.service.report.ReportGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final List<ReportGenerator> reportGenerators;

    public void prepareReport(Order order) {
        reportGenerators.forEach(reportGenerator -> reportGenerator.generateReport(order));
    }

}
