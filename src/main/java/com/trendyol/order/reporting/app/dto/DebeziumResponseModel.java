package com.trendyol.order.reporting.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebeziumResponseModel<T> implements Serializable {

    private T after;

    private T before;

}
