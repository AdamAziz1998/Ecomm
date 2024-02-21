package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.dto.CartItemDTO;
import com.azizONeill.cart.dto.CreateCartItemDTO;
import com.azizONeill.cart.dto.UpdateCartItemDTO;
import com.azizONeill.cart.dto.convert.DTOConverter;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import com.azizONeill.cart.repository.CartItemRepository;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public CartItemDTO createCartItem(CreateCartItemDTO createCartItemDTO) {

        Cart cart = this.cartRepository.findById(createCartItemDTO.getCartId()).orElse(null);

        if (cart == null) {
            return null;
        }

        CartItem newCartItem = new CartItem();

        newCartItem.setProductId(createCartItemDTO.getProductId());
        newCartItem.setQuantity(createCartItemDTO.getQuantity());
        newCartItem.setCart(cart);

        CartItem cartItem = this.cartItemRepository.save(newCartItem);

        return this.DTOConverter.convertCartItemToCartItemDTO(cartItem);
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
    public void deleteCartItem(UUID cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

        cartItemRepository.delete(cartItem);
    }

}
