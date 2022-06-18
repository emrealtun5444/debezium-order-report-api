package com.trendyol.order.reporting.app.service.report;

import com.trendyol.order.reporting.app.dto.Order;

public interface ReportGenerator {

    void generateReport(Order order);

}
