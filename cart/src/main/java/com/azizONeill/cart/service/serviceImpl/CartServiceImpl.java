package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.client.ProductClient;
import com.azizONeill.cart.dto.CartDTO;
import com.azizONeill.cart.dto.CartItemDTO;
import com.azizONeill.cart.dto.CreateCartDTO;
import com.azizONeill.cart.dto.ProductDTO;
import com.azizONeill.cart.dto.convert.DTOConverter;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import com.azizONeill.cart.repository.CartItemRepository;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final DTOConverter DTOConverter;
    private final ProductClient productClient;


    @Autowired
    public CartServiceImpl(
            CartRepository cartRepository,
            DTOConverter DTOConverter,
            ProductClient productClient
    ) {
        this.cartRepository = cartRepository;
        this.DTOConverter = DTOConverter;
        this.productClient = productClient;
    }

    @Override
    public CartDTO createCart(CreateCartDTO createCartDTO) {

        Cart cart = new Cart();

        Set<CartItemDTO> cartItemDTOS = createCartDTO.getCartItems();
        Set<CartItem> cartItems = cartItemDTOS.stream()
                .map(this.DTOConverter::convertCartItemDTOToCartItem).collect(Collectors.toSet());

        cart.setCartItems(cartItems);
        cart.setUserId(createCartDTO.getUserId());

        Cart newcart = this.cartRepository.save(cart);

        return this.DTOConverter.convertCartToCartDTO(newcart);
    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();

        return carts.stream().map(this.DTOConverter::convertCartToCartDTO).toList();
    }

    @Override
    @Cacheable("cartCache")
    public CartDTO getCartByCartId(UUID cartId) {

        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            return null;
        }

        return this.DTOConverter.convertCartToCartDTO(cart);
    }

    @Override
    public List<ProductDTO> getProductsByCartId(UUID cartId) {
        Cart cart = this.cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            return null;
        }

        Set<CartItem> cartItems = cart.getCartItems();

        return cartItems.stream().map(cartItem -> productClient.findProductById(cartItem.getProductId())).toList();
    }

    @Override
    @CachePut(value = "cartCache", key = "#cartId")
    public CartDTO clearCart(UUID cartId) {

        Cart cart = this.cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            return null;
        }

        Set<CartItem> cartItems = cart.getCartItems();
        cartItems.clear();

        Cart emptiedCart = cartRepository.save(cart);

        return this.DTOConverter.convertCartToCartDTO(emptiedCart);
    }

    @Override
    @CacheEvict(value = "cartItemCache", key = "#cartId")
    public void deleteCart(UUID cartId) {
        Cart cart = this.cartRepository.findById(cartId).orElse(null);

        cartRepository.delete(cart);

    }
}
