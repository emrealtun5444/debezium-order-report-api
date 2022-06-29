package com.trendyol.order.reporting.app.service.report;

import com.trendyol.order.reporting.app.dto.DebeziumResponseModel;
import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.OperationType;
import com.trendyol.order.reporting.app.model.SellerPerformance;
import com.trendyol.order.reporting.app.repository.SellerPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerPerformanceReportGenerator implements ReportGenerator {

    private final SellerPerformanceRepository sellerPerformanceRepository;

    @Override
    @Transactional
    public void generateReport(DebeziumResponseModel<Order> orderModel) {

        final var order = orderModel.getAfter();
        final var operationType = OperationType.getByValue(orderModel.getOp());

        final var sellerPerformanceOptional = sellerPerformanceRepository.findBySellerName(order.getSellerName());

        final var sellerPerformance = sellerPerformanceOptional.orElseGet(() ->
                SellerPerformance.builder()
                        .sellerName(order.getSellerName())
                        .build());
        sellerPerformance.executeOrder(order, operationType);
        sellerPerformanceRepository.save(sellerPerformance);
    }

    @Override
    public List<OperationType> getAllowedOperationType() {
        return List.of(OperationType.SNAPSHOT, OperationType.CREATE, OperationType.UPDATE);
    }

}
