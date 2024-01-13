package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.CreateCartItemDTO;

import java.util.UUID;

public interface CartService {
    CreateCartItemDTO addProductToCart(UUID cartId, CreateCartItemDTO createCartItemDTO);
}
