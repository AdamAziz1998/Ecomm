package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.client.ProductClient;
import com.azizONeill.cart.config.exceptions.ObjectValidation;
import com.azizONeill.cart.config.exceptions.exceptionTypes.CartItemAlreadyExistsException;
import com.azizONeill.cart.config.exceptions.exceptionTypes.ResourceNotFoundException;
import com.azizONeill.cart.dto.*;
import com.azizONeill.cart.dto.convert.DTOConverter;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import com.azizONeill.cart.repository.CartItemRepository;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductClient productClient;
    private final DTOConverter DTOConverter;
    private final CartRepository cartRepository;

    private final ObjectValidation<CreateCartItemDTO> createCartItemDTOObjectValidation;
    private final ObjectValidation<UpdateCartItemDTO> updateCartItemDTOObjectValidation;
    private final ObjectValidation<DeleteCartItemDTO> deleteCartItemDTOObjectValidation;

    @Override
    @Transactional
    public CartItemDTO createCartItem(CreateCartItemDTO createCartItemDTO) {
        createCartItemDTOObjectValidation.validate(createCartItemDTO);

        //check if the product which will be added exists
        productClient.findProductById(createCartItemDTO.getProductId());
        productClient.findProductVariantById(createCartItemDTO.getProductVariantId());

        UUID cartId = createCartItemDTO.getCartId();
        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));

        // Check if cartItem is already in cart
        if (cart.getCartItems().stream().anyMatch(cartItem -> createCartItemDTO.getProductId().equals(cartItem.getProductVariantId()))) {
            throw new CartItemAlreadyExistsException("Cart already contains product with id: " + createCartItemDTO.getProductVariantId());
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setProductId(createCartItemDTO.getProductId());
        newCartItem.setProductVariantId(createCartItemDTO.getProductVariantId());
        newCartItem.setQuantity(createCartItemDTO.getQuantity());

        cart.getCartItems().add(newCartItem);

        this.cartItemRepository.save(newCartItem);
        this.cartRepository.save(cart);

        return this.DTOConverter.convertCartItemToCartItemDTO(newCartItem);
    }

    @Override
    public CartItemDTO getCartItemByCartItemId(UUID cartItemId) {
        CartItem cartItem = this.cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResourceNotFoundException("cartItem not found with id " + cartItemId));

        return this.DTOConverter.convertCartItemToCartItemDTO(cartItem);
    }

    @Override
    public List<CartItemDTO> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();

        return cartItems.stream().map(DTOConverter::convertCartItemToCartItemDTO).toList();
    }

    @Override
    public List<CartItemDTO> getCartItemsByCartId(UUID cartId) {
        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));

        List<CartItem> cartItems = cart.getCartItems();

        return cartItems.stream().map(this.DTOConverter::convertCartItemToCartItemDTO).collect(Collectors.toList());
    }

    @Override
    public CartItemDTO updateCartItemQuantity(UpdateCartItemDTO updateCartItemDTO) {
        updateCartItemDTOObjectValidation.validate(updateCartItemDTO);

        UUID cartItemId = updateCartItemDTO.getCartItemId();
        CartItem cartItem = this.cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResourceNotFoundException("cartItem not found with id " + cartItemId));

        cartItem.setQuantity(updateCartItemDTO.getQuantity());
        CartItem updatedCartItem = cartItemRepository.save(cartItem);

        return this.DTOConverter.convertCartItemToCartItemDTO(updatedCartItem);
    }

    @Override
    public CartDTO deleteCartItem(DeleteCartItemDTO deleteCartItemDTO) {
        deleteCartItemDTOObjectValidation.validate(deleteCartItemDTO);

        UUID cartId = deleteCartItemDTO.getCartId();
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cartItem not found with id " + cartId));

        List<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = cartItems.stream()
                .filter(item -> deleteCartItemDTO.getCartItemId().equals(item.getId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("cartItem not found with id " + cartId));

        cartItems.remove(cartItem);
        Cart newCart = cartRepository.save(cart);

        return DTOConverter.convertCartToCartDTO(newCart);
    }

}
