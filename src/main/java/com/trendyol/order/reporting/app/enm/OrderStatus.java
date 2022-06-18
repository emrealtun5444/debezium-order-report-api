package com.trendyol.order.reporting.app.enm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    CREATED("Created"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    private String name;

}
