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

import java.util.List;
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

        //step 2: get the cartItems in the cart
        List<CartItem> cartItems = cart.getCartItems();

        //step 3: for each cartItem get the product by its id

    }

    @Override
    public CartDTO clearCart(UUID cartId) {

        Cart cart = this.cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            return null;
        }

        cart.getCartItems().clear();
        cartRepository.save(cart);

        return this.DTOConverter.convertCartToCartDTO(cart);
    }

    @Override
    public void deleteCart(UUID cartId) {
        Cart cart = this.cartRepository.findById(cartId).orElse(null);

        cartRepository.delete(cart);

    }
}
