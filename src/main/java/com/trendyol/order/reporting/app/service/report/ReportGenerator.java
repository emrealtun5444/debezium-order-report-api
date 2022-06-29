package com.trendyol.order.reporting.app.service.report;

import com.trendyol.order.reporting.app.dto.DebeziumResponseModel;
import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.OperationType;

import java.util.List;

public interface ReportGenerator {

    void generateReport(DebeziumResponseModel<Order> orderModel);

    List<OperationType> getAllowedOperationType();

}
