package com.trendyol.order.reporting.app.service;

import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.ReportType;
import com.trendyol.order.reporting.app.service.report.ReportGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final List<ReportGenerator> reportGenerators;

    public void prepareReport(Order order, ReportType reportType) {
        final var filteredReportTypes = reportGenerators
                .stream().filter(reportGenerator -> reportType.equals(reportGenerator.getReportType())).collect(Collectors.toList());

        filteredReportTypes.forEach(reportGenerator -> reportGenerator.generateReport(order));
    }

}
