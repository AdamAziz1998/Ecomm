package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.dto.*;
import com.azizONeill.cart.dto.convert.DTOConverter;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import com.azizONeill.cart.repository.CartItemRepository;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final DTOConverter DTOConverter;
    private final CartRepository cartRepository;


    @Autowired
    public CartItemServiceImpl(
            CartItemRepository cartItemRepository,
            DTOConverter DTOConverter,
            CartRepository cartRepository
    ) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.DTOConverter = DTOConverter;
    }

    @Override
    @CacheEvict(value = "productCartCache", key = "#createCartItemDTO.getCartId()")
    public CartItemDTO createCartItem(CreateCartItemDTO createCartItemDTO) {

        Cart cart = this.cartRepository.findById(createCartItemDTO.getCartId()).orElse(null);

        if (cart == null) {
            return null;
        }

        //check if cartItem is already in cart
        List<CartItem> cartItems = cart.getCartItems();
        List<CartItem> filteredCartItems = cartItems.stream().filter(cartItem -> createCartItemDTO.getProductId().equals(cartItem.getProductId())).toList();

        if (!filteredCartItems.isEmpty()) {
            return null;
        }

        CartItem newCartItem = new CartItem();

        newCartItem.setProductId(createCartItemDTO.getProductId());
        newCartItem.setQuantity(createCartItemDTO.getQuantity());

        cart.getCartItems().add(newCartItem);

        this.cartItemRepository.save(newCartItem);
        this.cartRepository.save(cart);

        return this.DTOConverter.convertCartItemToCartItemDTO(newCartItem);
    }

    @Override
    public CartItemDTO getCartItemByCartItemId(UUID cartItemId) {
        CartItem cartItem = this.cartItemRepository.findById(cartItemId).orElse(null);

        if (cartItem == null) {
            return null;
        }

        return this.DTOConverter.convertCartItemToCartItemDTO(cartItem);
    }

    @Override
    public List<CartItemDTO> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();

        return cartItems.stream().map(DTOConverter::convertCartItemToCartItemDTO).toList();
    }

    @Override
    public List<CartItemDTO> getCartItemsByCartId(UUID cartId) {
        Cart cart = this.cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();

        return cartItems.stream().map(this.DTOConverter::convertCartItemToCartItemDTO).collect(Collectors.toList());
    }

    @Override
    public CartItemDTO updateCartItemQuantity(UpdateCartItemDTO updateCartItemDTO) {
        CartItem cartItem = this.cartItemRepository.findById(updateCartItemDTO.getCartItemId()).orElse(null);

        if (cartItem == null) {
            return null;
        }

        cartItem.setQuantity(updateCartItemDTO.getQuantity());
        CartItem updatedCartItem = cartItemRepository.save(cartItem);

        return this.DTOConverter.convertCartItemToCartItemDTO(updatedCartItem);
    }

    @Override
    @CacheEvict(value = "productCartCache", key = "#deleteCartItemDTO.getCartId()")
    public CartDTO deleteCartItem(DeleteCartItemDTO deleteCartItemDTO) {

        Cart cart = cartRepository.findById(deleteCartItemDTO.getCartId()).orElse(null);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = cartItems.stream()
                .filter(item -> deleteCartItemDTO.getCartItemId().equals(item.getId()))
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            return null;
        }

        cartItems.remove(cartItem);
        Cart newCart = cartRepository.save(cart);

        return DTOConverter.convertCartToCartDTO(newCart);
    }

}
