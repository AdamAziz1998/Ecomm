package com.azizONeill.cart.convert;


import com.azizONeill.cart.dto.CartDTO;
import com.azizONeill.cart.model.Cart;
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

    public Cart convertCartDTOToCart (CartDTO cartDTO) {
        return modelMapper.map(cartDTO, Cart.class);
    }
}
