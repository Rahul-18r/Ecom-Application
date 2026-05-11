package com.app.ecom.controller;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.model.Product;
import com.app.ecom.service.CartService;
import com.app.ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartItemRequest  cartItemRequest;

    private final CartService cartService;
    @PostMapping
    public ResponseEntity<Void> addToCart(@RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest request) {
        cartService.addTOCart(userId,request);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
