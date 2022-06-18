package com.trendyol.order.reporting.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("seller_name")
    private String sellerName;

    private String status;

    private String category;

    @JsonProperty("product_code")
    private String productCode;

    @JsonProperty("product_name")
    private String productName;

    private Long quantity;

    private BigDecimal price;

}
