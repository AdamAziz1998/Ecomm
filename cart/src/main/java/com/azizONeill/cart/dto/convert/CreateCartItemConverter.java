package com.azizONeill.cart.dto.convert;

import com.azizONeill.cart.dto.CreateCartItemDTO;
import com.azizONeill.cart.model.CartItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateCartItemConverter {
    private final ModelMapper modelMapper;

    public CreateCartItemConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CreateCartItemDTO convertCreateCartItemToCreateCartItemDTO (CartItem cartItem) {
        return modelMapper.map(cartItem, CreateCartItemDTO.class);
    }

    public CartItem convertCreateCartItemDTOToCreateCartItem (CreateCartItemDTO createCartItemDTO) {
        return modelMapper.map(createCartItemDTO, CartItem.class);
    }
}
