package com.trendyol.order.reporting.app.service.report;

import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.ReportType;

public interface ReportGenerator {

    void generateReport(Order order);

    ReportType getReportType();

}
