package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.*;

import java.util.List;
import java.util.UUID;

public interface CartItemService {
    CartItemDTO createCartItem(CreateCartItemDTO createCartItemDTO);

    CartItemDTO getCartItemByCartItemId(UUID cartItemId);

    List<CartItemDTO> getAllCartItems();

    List<CartItemDTO> getCartItemsByCartId(UUID cartId);

    CartItemDTO updateCartItemQuantity(UpdateCartItemDTO updateCartItemDTO);

    CartDTO deleteCartItem(DeleteCartItemDTO deleteCartItemDTO);
}
