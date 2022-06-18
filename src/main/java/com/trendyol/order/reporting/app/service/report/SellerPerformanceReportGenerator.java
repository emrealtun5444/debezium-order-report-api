package com.trendyol.order.reporting.app.service.report;

import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.model.SellerPerformance;
import com.trendyol.order.reporting.app.repository.SellerPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerPerformanceReportGenerator implements ReportGenerator {

    private final SellerPerformanceRepository sellerPerformanceRepository;

    @Override
    @Transactional
    public void generateReport(Order order) {

        var sellerPerformanceOptional = sellerPerformanceRepository.findBySellerName(order.getSellerName());

        var sellerPerformance = sellerPerformanceOptional.orElseGet(() ->
                SellerPerformance.builder()
                        .sellerName(order.getSellerName())
                        .build());
        sellerPerformance.executeOrder(order);
        sellerPerformanceRepository.save(sellerPerformance);
    }
}
