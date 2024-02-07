package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.CartItemDTO;
import com.azizONeill.cart.dto.CreateCartItemDTO;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<CartItemDTO> getCartItemsByUserId(UUID userId);

    CreateCartItemDTO addProductToCart(UUID cartId, CreateCartItemDTO createCartItemDTO);
}
