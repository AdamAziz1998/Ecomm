package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.dto.*;
import com.azizONeill.cart.dto.convert.DTOConverter;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final DTOConverter DTOConverter;


    @Autowired
    public CartServiceImpl(
            CartRepository cartRepository,
            DTOConverter DTOConverter
    ) {
        this.cartRepository = cartRepository;
        this.DTOConverter = DTOConverter;
    }

    @Override
    public List<CartItemDTO> getCartItemsByUserId(UUID userId) {

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();

        return cartItems.stream().map(DTOConverter::convertCartItemToCartItemDTO).toList();
    }

    @Override
    public List<CartItemDTO> addToCart(CreateCartItemDTO createCartItemDTO) {

        Cart cart = this.cartRepository.findByUserId(createCartItemDTO.getUserId());

        CartItem newCartItem = new CartItem();
        newCartItem.setProductId(createCartItemDTO.getProductId());
        newCartItem.setQuantity(1);

        //If cart does not exist
        if (cart == null) {
            cart = new Cart();

            List<CartItem> cartItems = new ArrayList<>();
            cartItems.add(newCartItem);

            cart.setUserId(createCartItemDTO.getUserId());
            cart.setCartItems(cartItems);

            cartRepository.save(cart);

            return cartItems.stream().map(DTOConverter::convertCartItemToCartItemDTO).toList();
        }

        //If the cart does exist
        List<CartItem> cartItems = cart.getCartItems();
        CartItem filteredCartItem = cartItems.stream()
                .filter(cartItem -> cartItem.getProductId().equals(createCartItemDTO.getProductId()))
                .findFirst()
                .orElse(null);

        if (filteredCartItem == null) {
            //If the cart does exist but the cartItem does not exist
            cartItems.add(newCartItem);

            this.cartRepository.addProductToCart(cart.getId(), cartItems);
        } else {
            //The cart exists and the cartItem exists also
            this.cartRepository.updateCartItemQuantity(
                    filteredCartItem.getId(),
                    filteredCartItem.getQuantity() + 1);
        }

        return cartItems.stream().map(DTOConverter::convertCartItemToCartItemDTO).toList();
    }

    @Override
    public CartDTO clearCart(UUID userId) {

        Cart cart = this.cartRepository.findByUserId(userId);

        if (cart == null) {
            return null;
        }

        cart.getCartItems().clear();
        cartRepository.save(cart);

        return this.DTOConverter.convertCartToCartDTO(cart);
    }

    @Override
    public List<CartItemDTO> updateCartItemQuantity(UpdateCartItemDTO updateCartItemDTO) {
        Cart cart = cartRepository.findByUserId(updateCartItemDTO.getUserId());

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();
        CartItem filteredCartItem = cartItems.stream()
                .filter(cartItem -> cartItem.getProductId().equals(updateCartItemDTO.getProductId()))
                .findFirst()
                .orElse(null);

        if (filteredCartItem == null) {
            return null;
        }

        cartRepository.updateCartItemQuantity(filteredCartItem.getId(), updateCartItemDTO.getQuantity());

        List<CartItem> filteredCartItems = cartItems.stream()
                .map(cartItem -> cartItem.getProductId().equals(updateCartItemDTO.getProductId()) ? null : cartItem)
                .filter(Objects::nonNull)
                .toList();
        

        return filteredCartItems.stream().map(DTOConverter::convertCartItemToCartItemDTO).toList();
    }
    @Override
    public List<CartItemDTO> removeCartItem(RemoveCartItemDTO removeCartItemDTO) {
        Cart cart = cartRepository.findByUserId(removeCartItemDTO.getUserId());
        List<CartItem> cartItems = cart.getCartItems();

        CartItem filteredCartItem = cartItems.stream()
                .filter(cartItem -> cartItem.getProductId().equals(removeCartItemDTO.getProductId()))
                .findFirst()
                .orElse(null);

        if (filteredCartItem == null) {
            return null;
        }

        List<CartItem> filteredCartItems = cartItems.stream()
                .map(cartItem -> cartItem.getProductId().equals(removeCartItemDTO.getProductId()) ? null : cartItem)
                .filter(Objects::nonNull) // Remove null values
                .toList();

        cartRepository.removeProductFromCart(filteredCartItem.getId());

        return filteredCartItems.stream().map(DTOConverter::convertCartItemToCartItemDTO).toList();
    }

    @Override
    public void deleteCart(UUID cartId) {
        Cart cart = cartRepository.findByUserId(userId);

        //List<CartItem> cartItems = cart.getCartItems();

        //remove the cartItems related to that cart
        //cartItems.forEach(cartItem -> cartRepository.removeProductFromCart(cartItem.getId()));

        //remove the cart itself
        cartRepository.deleteByUserId(cart.getUserId());

    }
}
