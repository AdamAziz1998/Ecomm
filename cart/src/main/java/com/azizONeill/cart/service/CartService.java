package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.CreateCartItemDTO;
import com.azizONeill.cart.model.Cart;

import java.util.UUID;

public interface CartService {
    Cart getCartByUserId(UUID userId);

    CreateCartItemDTO addProductToCart(UUID cartId, CreateCartItemDTO createCartItemDTO);
}
