package com.trendyol.order.reporting.app.service.report;

import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.model.TopUser;
import com.trendyol.order.reporting.app.repository.TopUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopUserReportGenerator implements ReportGenerator {

    private final TopUserRepository topUserRepository;

    @Override
    @Transactional
    public void generateReport(Order order) {

        var topUserOptional = topUserRepository.findByUserName(order.getUserName());

        var topUser = topUserOptional.orElseGet(() ->
                TopUser.builder()
                .userName(order.getUserName())
                .build());

        topUser.executeOrder(order);
        topUserRepository.save(topUser);
    }
}
