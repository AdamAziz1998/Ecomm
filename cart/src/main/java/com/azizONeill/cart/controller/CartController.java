package com.azizONeill.cart.controller;

import com.azizONeill.cart.dto.*;
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
    public ResponseEntity<CreateCartDTO> createCart(@RequestBody CreateCartDTO) {

    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable UUID cartId) {
        this.cartService.deleteCart(cartId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/cart/{id}")
    public ResponseEntity<List<CartItemDTO>> addToCart(@PathVariable UUID id, @RequestBody AddToCartDTO addToCartDTO) {
        List<CartItemDTO> cartItemDTOS = this.cartService.addToCart(addToCartDTO);

        if (cartItemDTOS != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItemDTO>> getCartItemsByCartId(@PathVariable UUID userId) {
        List<CartItemDTO> cartItemDTOS = this.cartService.getCartItemsByUserId(userId);

        if (cartItemDTOS != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<CartDTO> delete(@PathVariable UUID cartId) {
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
}
// Change of plans, have the basic calls for each model, potentially create additional
// java classes to separate cart and cart item methods.
//
// Once all this is in place have a spacial method called getProductsInCart which uses the client
// getProductById, this return a list of productDTOs, this can also be defined in this project
//
// The reason I am going by this approach is so that each microservice is more loosely coupled.

// In addition to this, I will need to review some of the DTOs in the user and product
// service, there maybe a case where I will have to define more DTOs.



// Create cart
// get cart
// update cart
// Delete cart
// Get cartItems
// Create cart Items (this will add it to the corresponding cart)
// remove cart items
// update cart Items

