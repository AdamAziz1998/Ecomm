package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.convert.CartConverter;
import com.azizONeill.cart.convert.CartItemConverter;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemConverter cartItemConverter;
    private final CartConverter cartConverter;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemConverter cartItemConverter, CartConverter cartConverter) {
        this.cartRepository = cartRepository;
        this.cartConverter = cartConverter;
        this.cartItemConverter = cartItemConverter;
    }
}
