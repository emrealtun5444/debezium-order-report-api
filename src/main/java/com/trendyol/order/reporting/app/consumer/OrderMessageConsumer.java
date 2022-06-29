package com.trendyol.order.reporting.app.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.order.reporting.app.dto.DebeziumResponseModel;
import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.OperationType;
import com.trendyol.order.reporting.app.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderMessageConsumer {

    private final ReportService reportService;

    private CountDownLatch orderLatch = new CountDownLatch(1);

    @KafkaListener(topics = "${order.topic.name}", containerFactory = "fooKafkaListenerContainerFactory")
    public void orderListener(String json) {

        final var orderModel = getResponseModel(json);
        if (Objects.nonNull(orderModel) && OperationType.isAcceptableOperation(orderModel.getOp())) {

            reportService.prepareReport(orderModel);
        }
        this.orderLatch.countDown();
    }

    private DebeziumResponseModel<Order> getResponseModel(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(json, new TypeReference<DebeziumResponseModel<Order>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("cannot parse json");
        }
        return null;
    }

}
