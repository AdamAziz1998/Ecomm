package com.azizONeill.cart.controller;

import com.azizONeill.cart.dto.CartItemDTO;
import com.azizONeill.cart.dto.CreateCartItemDTO;
import com.azizONeill.cart.dto.UpdateCartItemDTO;
import com.azizONeill.cart.service.CartItemService;
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

        if (cartItemDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/cartItem/{cartItemId}")
    public ResponseEntity<CartItemDTO> getCartItemByCartItemId(@PathVariable UUID cartItemId) {
        CartItemDTO cartItemDTO = this.cartItemService.getCartItemByCartItemId(cartItemId);

        if (cartItemDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/cartItem")
    public ResponseEntity<List<CartItemDTO>> getAllCartItems() {
        List<CartItemDTO> cartItemDTOs = this.cartItemService.getAllCartItems();

        return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOs);
    }

    @GetMapping("/cartItem/cart/{cartId}")
    public ResponseEntity<List<CartItemDTO>> getCartItemsByCartId(@PathVariable UUID cartId) {
        List<CartItemDTO> cartItemDTOS = this.cartItemService.getCartItemsByCartId(cartId);

        if (cartItemDTOS != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/cartItem")
    public ResponseEntity<CartItemDTO> updateCartItemQuantity(@RequestBody UpdateCartItemDTO updateCartItemDTO) {
        CartItemDTO cartItemDTO = this.cartItemService.updateCartItemQuantity(updateCartItemDTO);

        if (cartItemDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/cartItem/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable UUID cartItemId) {
        CartItemDTO cartItemDTO = this.cartItemService.deleteCartItem(cartItemId);

        if (cartItemDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
