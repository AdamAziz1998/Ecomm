package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.convert.CreateCartItemConverter;
import com.azizONeill.cart.dto.CartItemDTO;
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
    private final CreateCartItemConverter createCartItemConverter;


    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CreateCartItemConverter createCartItemConverter) {
        this.cartRepository = cartRepository;
        this.createCartItemConverter = createCartItemConverter;
    }

    @Override
    public List<CartItemDTO> getCartItemsByUserId(UUID userId) {

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();

        return this.ca
    }

    @Override
    public CreateCartItemDTO addProductToCart(UUID cartId, CreateCartItemDTO createCartItemDTO) {

        Cart cart = this.cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();

        CartItem newCartItem = new CartItem();
        newCartItem.setProductId(createCartItemDTO.getProductId());
        newCartItem.setQuantity(createCartItemDTO.getQuantity());

        cartItems.add(newCartItem);

        CartItem cartItem = this.cartRepository.addProductToCart(cartId, cartItems);

        return this.createCartItemConverter.convertCartItemToCartItemDTO(cartItem);
    }


//    Cart deleteByUserId(UUID userId);
//    Cart addProductToCart(
//    Cart updateCartItemQuantity(
//    Cart removeProductFromCart(
}
