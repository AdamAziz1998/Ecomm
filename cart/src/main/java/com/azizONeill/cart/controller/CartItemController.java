package com.azizONeill.cart.controller;

import com.azizONeill.cart.dto.*;
import com.azizONeill.cart.service.CartItemService;
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
@Slf4j
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/cartItem")
    public ResponseEntity<CartItemDTO> createCartItem(@RequestBody CreateCartItemDTO createCartItemDTO) {

        CartItemDTO cartItemDTO = cartItemService.createCartItem(createCartItemDTO);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemDTO);
    }

    @GetMapping("/cartItem/{cartItemId}")
    public ResponseEntity<CartItemDTO> getCartItemByCartItemId(@PathVariable UUID cartItemId) {

        CartItemDTO cartItemDTO = this.cartItemService.getCartItemByCartItemId(cartItemId);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemDTO);
    }

    @GetMapping("/cartItem")
    public ResponseEntity<List<CartItemDTO>> getAllCartItems() {

        List<CartItemDTO> cartItemDTOs = this.cartItemService.getAllCartItems();
        return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOs);
    }

    @GetMapping("/cartItem/cart/{cartId}")
    public ResponseEntity<List<CartItemDTO>> getCartItemsByCartId(@PathVariable UUID cartId) {

        List<CartItemDTO> cartItemDTOS = this.cartItemService.getCartItemsByCartId(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOS);

    }

    @PutMapping("/cartItem")
    public ResponseEntity<CartItemDTO> updateCartItemQuantity(@RequestBody UpdateCartItemDTO updateCartItemDTO) {

        CartItemDTO cartItemDTO = this.cartItemService.updateCartItemQuantity(updateCartItemDTO);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemDTO);
    }

    @DeleteMapping("/cartItem")
    public ResponseEntity<CartDTO> deleteCartItem(@RequestBody DeleteCartItemDTO deleteCartItemDTO) {

        CartDTO cartDTO = this.cartItemService.deleteCartItem(deleteCartItemDTO);
        return ResponseEntity.status(HttpStatus.OK).body(cartDTO);
    }
}
