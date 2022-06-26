package com.trendyol.order.reporting.app.service.report;

import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.ReportType;
import com.trendyol.order.reporting.app.model.TopProduct;
import com.trendyol.order.reporting.app.repository.TopProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopProductReportGenerator implements ReportGenerator {

    private final TopProductRepository topProductRepository;

    @Override
    @Transactional
    public void generateReport(Order order) {
        var topProductOptional = topProductRepository.findByProductCode(order.getProductCode());

        var topProduct = topProductOptional.orElseGet(() ->
                TopProduct.builder()
                .productName(order.getProductName())
                .productCode(order.getProductCode())
                .build());
        topProduct.executeOrder(order);
        topProductRepository.save(topProduct);
    }

    @Override
    public ReportType getReportType() {
        return ReportType.CREATE;
    }
}
