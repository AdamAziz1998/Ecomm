package com.azizONeill.cart.controller;

import com.azizONeill.cart.dto.CartDTO;
import com.azizONeill.cart.dto.CreateCartDTO;
import com.azizONeill.cart.dto.ProductDTO;
import com.azizONeill.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<CartDTO> createCart(@RequestBody CreateCartDTO createCartDTO) {

        CartDTO cartDTO = cartService.createCart(createCartDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDTO);
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartDTO>> getAllCarts() {

        List<CartDTO> cartDTOs = cartService.getAllCarts();
        return ResponseEntity.status(HttpStatus.OK).body(cartDTOs);
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<CartDTO> getCartByCartId(@PathVariable UUID cartId) {

        CartDTO cartDTO = cartService.getCartByCartId(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartDTO);
    }

    @GetMapping("/cart/products/{cartId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCartId(@PathVariable UUID cartId) {

        List<ProductDTO> productDTOs = this.cartService.getProductsByCartId(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }

    @DeleteMapping("/cart/clear/{cartId}")
    public ResponseEntity<CartDTO> clearCart(@PathVariable UUID cartId) {

        CartDTO cartDTO = this.cartService.clearCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartDTO);
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable UUID cartId) {

        this.cartService.deleteCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
