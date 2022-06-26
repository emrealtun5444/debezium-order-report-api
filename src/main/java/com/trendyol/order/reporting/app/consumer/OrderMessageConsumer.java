package com.trendyol.order.reporting.app.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.order.reporting.app.dto.DebeziumResponseModel;
import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.ReportType;
import com.trendyol.order.reporting.app.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@RequiredArgsConstructor
public class OrderMessageConsumer {

    private final ReportService reportService;

    private CountDownLatch orderLatch = new CountDownLatch(1);

    @KafkaListener(topics = "${order.topic.name}", containerFactory = "fooKafkaListenerContainerFactory")
    public void orderListener(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            final var orderModel = objectMapper.readValue(json, new TypeReference<DebeziumResponseModel<Order>>() {});
            final var reportType = orderModel.getBefore() != null ? ReportType.UPDATE : ReportType.CREATE;
            reportService.prepareReport(orderModel.getAfter(), reportType);
            this.orderLatch.countDown();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
