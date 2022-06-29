package com.trendyol.order.reporting.app.enm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationType {
    CREATE("c"),
    UPDATE("u"),
    DELETE("d");

    private String value;
}
