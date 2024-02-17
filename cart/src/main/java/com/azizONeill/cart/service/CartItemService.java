package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.CartItemDTO;
import com.azizONeill.cart.dto.CreateCartItemDTO;
import com.azizONeill.cart.dto.UpdateCartItemDTO;

import java.util.List;
import java.util.UUID;

public interface CartItemService {
    CartItemDTO createCartItem(CreateCartItemDTO createCartItemDTO);

    CartItemDTO getCartItemByCartItemId(UUID cartItemId);

    List<CartItemDTO> getCartItemsByCartId(UUID cartId);

    CartItemDTO updateCartItemQuantity(UpdateCartItemDTO updateCartItemDTO);

    void deleteCartItem(UUID cartId);
}
