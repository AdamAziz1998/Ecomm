package com.azizONeill.cart.convert;

import com.azizONeill.cart.dto.CartItemDTO;
import com.azizONeill.cart.model.CartItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {
    private final ModelMapper modelMapper;

    public CartItemConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CartItemDTO convertCartItemToCartItemDTO (CartItem cartItem) {
        return modelMapper.map(cartItem, CartItemDTO.class);
    }

    public CartItem convertCartItemDTOToCartItem (CartItemDTO cartItemDTO) {
        return modelMapper.map(cartItemDTO, CartItem.class);
    }
}
