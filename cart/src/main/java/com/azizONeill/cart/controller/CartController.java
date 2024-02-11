package com.azizONeill.cart.controller;

import com.azizONeill.cart.dto.*;
import com.azizONeill.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/{userId}")
    public ResponseEntity<List<CartItemDTO>> getCartItemsByUserId(@PathVariable UUID userId) {
        List<CartItemDTO> cartItemDTOS = this.cartService.getCartItemsByUserId(userId);

        if (cartItemDTOS != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<List<CartItemDTO>> addToCart(AddToCartDTO addToCartDTO) {
        List<CartItemDTO> cartItemDTOS = this.cartService.addToCart(addToCartDTO);

        if (cartItemDTOS != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<CartDTO> clearCart(UUID userId) {
        CartDTO cartDTO = this.cartService.clearCart(userId);

        if (cartDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<List<CartItemDTO>> updateCartItemQuantity(UpdateCartItemDTO updateCartItemDTO) {
        List<CartItemDTO> cartItemDTOS = this.cartService.updateCartItemQuantity(updateCartItemDTO);

        if (cartItemDTOS != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<List<CartItemDTO>> removeCartItem(RemoveCartItemDTO removeCartItemDTO) {
        List<CartItemDTO> cartItemDTOS = this.cartService.removeCartItem(removeCartItemDTO);

        if (cartItemDTOS != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<?> deleteCart(UUID userId) {
        this.cartService.deleteCart(userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

