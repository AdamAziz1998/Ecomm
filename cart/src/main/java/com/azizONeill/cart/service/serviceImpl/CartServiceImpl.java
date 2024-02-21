package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.client.ProductClient;
import com.azizONeill.cart.dto.*;
import com.azizONeill.cart.dto.convert.DTOConverter;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import com.azizONeill.cart.repository.CartItemRepository;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartItemService;
import com.azizONeill.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final DTOConverter DTOConverter;
    private final ProductClient productClient;


    @Autowired
    public CartServiceImpl(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            DTOConverter DTOConverter,
            ProductClient productClient
    ) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.DTOConverter = DTOConverter;
        this.productClient = productClient;
    }

    @Override
    public CartDTO createCart(CreateCartDTO createCartDTO) {

        Cart cart = new Cart();

        List<CartItemDTO> cartItemDTOS = createCartDTO.getCartItems();
        List<CartItem> cartItems = cartItemDTOS.stream()
                .map(this.DTOConverter::convertCartItemDTOToCartItem).toList();

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
    public CartDTO getCartByCartId(UUID cartId) {

        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            return null;
        }

        return this.DTOConverter.convertCartToCartDTO(cart);
    }

    @Override
    public List<ProductDTO> getProductsByCartId(UUID cartId) {
        //step 1: get the cart
        Cart cart = this.cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();

        return cartItems.stream().map(cartItem -> productClient.findProductById(cartItem.getProductId())).toList();
    }

    @Override
    public CartDTO clearCart(UUID cartId) {

        Cart cart = this.cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();
        cartItems.forEach(cartItemRepository::delete);

        cart.setCartItems(new ArrayList<>());
        return this.DTOConverter.convertCartToCartDTO(cart);
    }

    @Override
    public void deleteCart(UUID cartId) {
        Cart cart = this.cartRepository.findById(cartId).orElse(null);

        cartRepository.delete(cart);

    }
}
