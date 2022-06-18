
package com.trendyol.order.reporting.app.model;

import com.trendyol.order.reporting.app.common.model.AbstractEntity;
import com.trendyol.order.reporting.app.dto.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class TopCategory extends AbstractEntity {

    private String categoryName;

    @Builder.Default
    private BigDecimal totalSellingAmount = BigDecimal.ZERO;
    @Builder.Default
    private Long totalSellingCount = 0L;

    public void executeOrder(Order order) {
        setTotalSellingCount(totalSellingCount + order.getQuantity());
        setTotalSellingAmount(totalSellingAmount.add(order.getPrice()));
    }

}
