package com.azizONeill.cart.controller;

import com.azizONeill.cart.dto.*;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<CartDTO> createCart(@RequestBody CreateCartDTO createCartDTO) {
        CartDTO cartDTO = cartService.createCart(createCartDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartDTO);
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<CartDTO> getCartByCartId(@PathVariable UUID cartId) {

    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCartId(@PathVariable UUID cartId) {

    }

    @PutMapping("/cart/add/{id}")
    public ResponseEntity<List<CartItemDTO>> addToCart(@PathVariable UUID id, @RequestBody CreateCartItemDTO createCartItemDTO) {
        List<CartItemDTO> cartItemDTOS = this.cartService.addToCart(createCartItemDTO);

        if (cartItemDTOS != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/cart/clear/{id}")
    public ResponseEntity<CartDTO> clearCart(@PathVariable UUID cartId) {
        CartDTO cartDTO = this.cartService.clearCart(userId);

        if (cartDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable UUID cartId) {
        this.cartService.deleteCart(cartId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
// Change of plans, have the basic calls for each model, potentially create additional
// java classes to separate cart and cart item methods.
//
// Once all this is in place have a spacial method called getProductsInCart which uses the client
// getProductById, this return a list of productDTOs, this can also be defined in this project
//
// The reason I am going by this approach is so that each microservice is more loosely coupled.
