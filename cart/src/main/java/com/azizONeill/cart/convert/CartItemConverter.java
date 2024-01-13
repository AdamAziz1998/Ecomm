package com.azizONeill.cart.convert;

import com.azizONeill.cart.dto.CreateCartItemDTO;
import com.azizONeill.cart.model.CartItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {
    private final ModelMapper modelMapper;

    public CartItemConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CreateCartItemDTO convertCartItemToCartItemDTO (CartItem cartItem) {
        return modelMapper.map(cartItem, CreateCartItemDTO.class);
    }

    public CartItem convertCartItemDTOToCartItem (CreateCartItemDTO createCartItemDTO) {
        return modelMapper.map(createCartItemDTO, CartItem.class);
    }
}
