package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.convert.CartConverter;
import com.azizONeill.cart.convert.CartItemConverter;
import com.azizONeill.cart.dto.CreateCartItemDTO;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    @Override
    public Cart getCartByUserId(UUID userId);

    @Override
    public CreateCartItemDTO addProductToCart(UUID cartId, CreateCartItemDTO createCartItemDTO) {

        log.info("addProductToCart started");

        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();

        CartItem newCartItem = new CartItem();
        newCartItem.setProductId(createCartItemDTO.getProductId());
        newCartItem.setQuantity(createCartItemDTO.getQuantity());

        cartItems.add(newCartItem);

        CartItem cartItem = cartRepository.addProductToCart(cartId, cartItems);

        return cartItemConverter.convertCartItemToCartItemDTO(cartItem);
    }


//    Cart findByUserId(UUID userId);
//    Cart deleteByUserId(UUID userId);
//    Cart addProductToCart(
//    Cart updateCartItemQuantity(
//    Cart removeProductFromCart(
}
