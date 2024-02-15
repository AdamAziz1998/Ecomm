package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.*;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<CartItemDTO> getCartItemsByUserId(UUID userId);

    List<CartItemDTO> addToCart(CreateCartItemDTO createCartItemDTO);

    CartDTO clearCart(UUID userId);

    List<CartItemDTO> updateCartItemQuantity(UpdateCartItemDTO updateCartItemDTO);

    List<CartItemDTO> removeCartItem(RemoveCartItemDTO removeCartItemDTO);

    void deleteCart(UUID userId);
}
