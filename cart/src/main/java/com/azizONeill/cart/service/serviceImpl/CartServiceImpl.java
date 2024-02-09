package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.dto.AddToCartDTO;
import com.azizONeill.cart.dto.CartDTO;
import com.azizONeill.cart.dto.convert.CartConverter;
import com.azizONeill.cart.dto.CartItemDTO;
import com.azizONeill.cart.dto.CreateCartItemDTO;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartConverter cartConverter;


    @Autowired
    public CartServiceImpl(
            CartRepository cartRepository,
            CartConverter cartConverter
    ) {
        this.cartRepository = cartRepository;
        this.cartConverter = cartConverter;
    }

    @Override
    public List<CartItemDTO> getCartItemsByUserId(UUID userId) {

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();

        return cartItems.stream().map(cartConverter::convertCartItemToCartItemDTO).collect(Collectors.toList());
    }

    @Override
    public CartItemDTO addToCart(AddToCartDTO addToCartDTO) {

        Cart cart = this.cartRepository.findByUserId(addToCartDTO.getUserId());

        if (cart == null) {
            return null;
        }

        //if cart already exists
        List<CartItem> cartItems = cart.getCartItems();
        CartItem filteredCartItem = cartItems.stream()
                .filter(cartItem -> cartItem.getProductId().equals(addToCartDTO.getProductId()))
                .findFirst()
                .orElse(null);

        if (filteredCartItem == null) {
            CartItem newCartItem = new CartItem();
            newCartItem.setProductId(addToCartDTO.getProductId());
            newCartItem.setQuantity(addToCartDTO.getQuantity());
            cartItems.add(newCartItem);

            CartItem cartItem = this.cartRepository.addProductToCart(cart.getId(), cartItems);
        } else {
            CartItem cartItem = this.cartRepository.updateCartItemQuantity(
                    filteredCartItem.getId(),
                    filteredCartItem.getQuantity() + 1);
        }


        return this.cartConverter.convertCartItemToCartItemDTO(cartItem);
    }

    @Override
    public CartDTO clearCart(UUID userId) {

        Cart cart = this.cartRepository.findByUserId(userId);

        if (cart == null) {
            return null;
        }

        cart.getCartItems().clear();
        cartRepository.save(cart);

        return this.cartConverter.convertCartToCartDTO(cart);
    }

    @Override
    public CartDTO createCart(CartDTO cartDTO) {

    }

    @Override
    public List<CartItem> updateCartItemQuantity(UUID userId, UUID productId, int quantity) {
        //dto the input
    }
    @Override
    public List<CartItem> removeCartItem(String userId, UUID productId) {
        //dto the input
        //TODO: Potentially have only a DTO as an input and have the converter methods all into 1
        // (in config maybe)
    }
}
