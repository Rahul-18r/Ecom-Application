package com.app.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;

}
