package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.AddToCartDTO;
import com.azizONeill.cart.dto.CartDTO;
import com.azizONeill.cart.dto.CartItemDTO;
import com.azizONeill.cart.dto.CreateCartItemDTO;
import com.azizONeill.cart.model.CartItem;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<CartItemDTO> getCartItemsByUserId(UUID userId);

    CreateCartItemDTO addToCart(AddToCartDTO addToCartDTO);

    List<CartItem> updateCartItemQuantity(UUID userId, UUID productId, int quantity);
}
