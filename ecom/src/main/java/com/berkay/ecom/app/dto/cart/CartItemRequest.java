package com.berkay.ecom.app.dto.cart;

import lombok.Data;

@Data
public class CartItemRequest {

    private Long productId;
    private Integer quantity;

}
