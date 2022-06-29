package com.trendyol.order.reporting.app.service.report;

import com.trendyol.order.reporting.app.dto.DebeziumResponseModel;
import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.OperationType;
import com.trendyol.order.reporting.app.model.TopUser;
import com.trendyol.order.reporting.app.repository.TopUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopUserReportGenerator implements ReportGenerator {

    private final TopUserRepository topUserRepository;

    @Override
    @Transactional
    public void generateReport(DebeziumResponseModel<Order> orderModel) {

        final var order = orderModel.getAfter();

        var topUserOptional = topUserRepository.findByUserName(order.getUserName());

        var topUser = topUserOptional.orElseGet(() ->
                TopUser.builder()
                        .userName(order.getUserName())
                        .build());

        topUser.executeOrder(order);
        topUserRepository.save(topUser);
    }

    @Override
    public List<OperationType> getAllowedOperationType() {
        return List.of(OperationType.SNAPSHOT, OperationType.CREATE);
    }
}
