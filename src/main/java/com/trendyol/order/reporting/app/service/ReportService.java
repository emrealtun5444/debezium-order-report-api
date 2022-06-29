package com.trendyol.order.reporting.app.service;

import com.trendyol.order.reporting.app.dto.DebeziumResponseModel;
import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.OperationType;
import com.trendyol.order.reporting.app.service.report.ReportGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final List<ReportGenerator> reportGenerators;

    public void prepareReport(DebeziumResponseModel<Order> orderModel) {

        final var operationType = OperationType.getByValue(orderModel.getOp());
        final var filteredReportTypes = reportGenerators
                .stream().filter(reportGenerator ->
                        reportGenerator.getAllowedOperationType().contains(operationType)).collect(Collectors.toList());

        filteredReportTypes.forEach(reportGenerator -> reportGenerator.generateReport(orderModel));
    }

}
