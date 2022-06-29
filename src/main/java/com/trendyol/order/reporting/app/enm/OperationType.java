package com.trendyol.order.reporting.app.enm;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OperationType {
    CREATE("c"),
    UPDATE("u"),
    DELETE("d"),
    SNAPSHOT("r");

    private String value;

    public static boolean isAcceptableOperation(String op) {
        return CREATE.getValue().equals(op) || UPDATE.getValue().equals(op) || SNAPSHOT.getValue().equals(op);
    }

    public static OperationType getByValue(String op) {
        return Arrays.stream(values())
                .filter(value -> value.getValue().equals(op))
                .findFirst()
                .orElse(null);
    }
}
