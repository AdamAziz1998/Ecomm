package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.dto.CartDTO;
import com.azizONeill.cart.dto.convert.CartConverter;
import com.azizONeill.cart.dto.convert.CartItemConverter;
import com.azizONeill.cart.dto.convert.CreateCartItemConverter;
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
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CreateCartItemConverter createCartItemConverter;
    private final CartItemConverter cartItemConverter;
    private final CartConverter cartConverter;


    @Autowired
    public CartServiceImpl(
            CartRepository cartRepository,
            CreateCartItemConverter createCartItemConverter,
            CartItemConverter cartItemConverter,
            CartConverter cartConverter
    ) {
        this.cartRepository = cartRepository;
        this.createCartItemConverter = createCartItemConverter;
        this.cartItemConverter = cartItemConverter;
        this.cartConverter = cartConverter;
    }

    @Override
    public List<CartItemDTO> getCartItemsByUserId(UUID userId) {

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems = cart.getCartItems();

        return cartItems.stream().map(cartItemConverter::convertCartItemToCartItemDTO).collect(Collectors.toList());
    }

    @Override
    public CreateCartItemDTO addToCart(UUID cartId, CreateCartItemDTO createCartItemDTO) {

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

        return this.createCartItemConverter.convertCreateCartItemToCreateCartItemDTO(cartItem);
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
