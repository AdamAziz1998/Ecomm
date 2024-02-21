package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.*;

import java.util.List;
import java.util.UUID;

public interface CartService {

    CartDTO createCart(CreateCartDTO createCartDTO);

    List<CartDTO> getAllCarts();

    CartDTO getCartByCartId(UUID cartId);

    List<ProductDTO> getProductsByCartId(UUID cartId);

    CartDTO clearCart(UUID cartId);

    void deleteCart(UUID cartId);
}
