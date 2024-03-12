package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.CartDTO;
import com.azizONeill.cart.dto.CreateCartDTO;
import com.azizONeill.cart.dto.ProductDTO;

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
