package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.client.ProductClient;
import com.azizONeill.cart.config.exceptions.exceptionTypes.ResourceNotFoundException;
import com.azizONeill.cart.config.exceptions.ObjectValidation;
import com.azizONeill.cart.dto.*;
import com.azizONeill.cart.dto.convert.DTOConverter;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final DTOConverter DTOConverter;
    private final ProductClient productClient;
    private final ObjectValidation<CreateCartDTO> createCartDTOObjectValidation;

    @Override
    public CartDTO createCart(CreateCartDTO createCartDTO) {
        createCartDTOObjectValidation.validate(createCartDTO);

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

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cartId not found with id " + cartId));
        return this.DTOConverter.convertCartToCartDTO(cart);
    }

    @Override
    @Cacheable("productCartCache")
    public List<ProductDTO> getProductsByCartId(UUID cartId) {

        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cartId not found with id " + cartId));
        List<CartItem> cartItems = cart.getCartItems();
        List<ProductDTO> products = cartItems.stream().map(cartItem -> productClient.findProductById(cartItem.getProductId())).toList();

    }

    @Override
    @CacheEvict(value = "productCartCache", key = "#cartId")
    public CartDTO clearCart(UUID cartId) {

        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cartId not found with id " + cartId));

        List<CartItem> cartItems = cart.getCartItems();
        cartItems.clear();

        Cart emptiedCart = cartRepository.save(cart);

        return this.DTOConverter.convertCartToCartDTO(emptiedCart);
    }

    @Override
    @CacheEvict(value = "productCartCache", key = "#cartId")
    public void deleteCart(UUID cartId) {

        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cartId not found with id " + cartId));
        cartRepository.delete(cart);
    }
}
