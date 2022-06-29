package com.trendyol.order.reporting.app.service.report;

import com.trendyol.order.reporting.app.dto.DebeziumResponseModel;
import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.OperationType;
import com.trendyol.order.reporting.app.model.TopSeller;
import com.trendyol.order.reporting.app.repository.TopSellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopSellerReportGenerator implements ReportGenerator {

    private final TopSellerRepository topSellerRepository;

    @Override
    @Transactional
    public void generateReport(DebeziumResponseModel<Order> orderModel) {

        final var order = orderModel.getAfter();

        var topSellerOptional = topSellerRepository.findBySellerName(order.getSellerName());

        var topSeller = topSellerOptional.orElseGet(() ->
                TopSeller.builder()
                .sellerName(order.getSellerName())
                .build());
        topSeller.executeOrder(order);
        topSellerRepository.save(topSeller);
    }

    @Override
    public List<OperationType> getAllowedOperationType() {
        return List.of(OperationType.SNAPSHOT, OperationType.CREATE);
    }
}
