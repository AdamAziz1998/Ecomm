package com.azizONeill.cart.service;

import com.azizONeill.cart.dto.*;

import java.util.List;
import java.util.UUID;

public interface CartService {

    CartDTO createCart(CreateCartDTO createCartDTO);

    CartDTO getCartByCartId(UUID cartId);
}
