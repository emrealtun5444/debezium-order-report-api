package com.trendyol.order.reporting.app.service.report;

import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.model.TopCategory;
import com.trendyol.order.reporting.app.repository.TopCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopCategoryReportGenerator implements ReportGenerator {

    private final TopCategoryRepository topCategoryRepository;

    @Override
    @Transactional
    public void generateReport(Order order) {

        var topCategoryOptional = topCategoryRepository.findByCategoryName(order.getCategory());

        var topCategory = topCategoryOptional.orElseGet(() -> TopCategory.builder()
                .categoryName(order.getCategory())
                .build());
        topCategory.executeOrder(order);
        topCategoryRepository.save(topCategory);

    }
}
