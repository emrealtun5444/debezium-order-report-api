package com.trendyol.order.reporting.app.model;

import com.trendyol.order.reporting.app.common.model.AbstractEntity;
import com.trendyol.order.reporting.app.dto.Order;
import com.trendyol.order.reporting.app.enm.OperationType;
import com.trendyol.order.reporting.app.enm.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class SellerPerformance extends AbstractEntity {

    private String sellerName;

    @Builder.Default
    private Long totalOrderCreated = 0L;
    @Builder.Default
    private Long totalOrderShipped = 0L;
    @Builder.Default
    private Long totalOrderDelivered = 0L;
    @Builder.Default
    private Long totalOrder = 0L;

    public void executeOrder(Order order, OperationType operationType) {

        if (OrderStatus.CREATED.getName().equals(order.getStatus())) {
            setTotalOrderCreated(increase(getTotalOrderCreated()));
            setTotalOrder(increase(getTotalOrder()));
        }

        if (OrderStatus.SHIPPED.getName().equals(order.getStatus())) {
            if (!OperationType.SNAPSHOT.equals(operationType))
                setTotalOrderCreated(decrease(getTotalOrderCreated()));
            setTotalOrderShipped(increase(getTotalOrderShipped()));
        }

        if (OrderStatus.DELIVERED.getName().equals(order.getStatus())) {
            if (!OperationType.SNAPSHOT.equals(operationType))
                setTotalOrderShipped(decrease(getTotalOrderShipped()));
            setTotalOrderDelivered(increase(getTotalOrderDelivered()));
        }

    }

    private Long increase(Long number) {
        return number + 1;
    }

    private Long decrease(Long number) {
        return number - 1;
    }

}
