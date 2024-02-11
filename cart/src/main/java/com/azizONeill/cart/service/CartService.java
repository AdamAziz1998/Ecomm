package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.*;
import com.azizONeill.cart.model.CartItem;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<CartItemDTO> getCartItemsByUserId(UUID userId);

    List<CartItemDTO> addToCart(AddToCartDTO addToCartDTO);

    CartDTO clearCart(UUID userId);

    CartDTO createCart(CartDTO cartDTO);

    List<CartItemDTO> updateCartItemQuantity(UpdateCartItemDTO updateCartItemDTO);

    List<CartItemDTO> removeCartItem(RemoveCartItemDTO removeCartItemDTO);
}
