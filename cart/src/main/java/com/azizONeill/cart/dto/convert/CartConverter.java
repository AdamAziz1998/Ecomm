package com.azizONeill.cart.dto.convert;

import com.azizONeill.cart.dto.CartDTO;
import com.azizONeill.cart.dto.CartItemDTO;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {
    private final ModelMapper modelMapper;

    public CartConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CartDTO convertCartToCartDTO (Cart cart) {
        return modelMapper.map(cart, CartDTO.class);
    }

    public CartItemDTO convertCartItemToCartItemDTO (CartItem cartItem) {
        return modelMapper.map(cartItem, CartItemDTO.class);
    }



}
