package com.azizONeill.cart.service.serviceImpl;

import com.azizONeill.cart.client.ProductClient;
import com.azizONeill.cart.config.exceptions.ObjectValidation;
import com.azizONeill.cart.config.exceptions.exceptionTypes.ResourceNotFoundException;
import com.azizONeill.cart.dto.*;
import com.azizONeill.cart.dto.convert.DTOConverter;
import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import com.azizONeill.cart.repository.CartRepository;
import com.azizONeill.cart.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public FullCartInformationDTO getProductsByCartId(UUID cartId) {

        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cartId not found with id: " + cartId));
        List<CartItem> cartItems = cart.getCartItems();

        FullCartInformationDTO fullCartInformationDTO = new FullCartInformationDTO();

        List<ProductCartDTO> productCartDTOs = cartItems.stream().map(cartItem -> {
            ProductCartDTO productCartDTO = new ProductCartDTO();
            ProductDTO productDTO = productClient.findProductById(cartItem.getProductId());
            ProductVariantDTO productVariantDTO = productClient.findProductVariantById(cartItem.getProductVariantId());
            productCartDTO.setProductId(cartItem.getProductId());
            productCartDTO.setProductVariantId(cartItem.getProductVariantId());
            productCartDTO.setName(productDTO.getName());
            productCartDTO.setImageUrl(productDTO.getImageUrl());
            productCartDTO.setStatus(productVariantDTO.getStatus());
            productCartDTO.setPrice(productVariantDTO.getPrice());

            productCartDTO.setColor(productVariantDTO.getColor());
            productCartDTO.setSize(productVariantDTO.getSize());
            productCartDTO.setFlavour(productVariantDTO.getFlavour());
            productCartDTO.setQuantity(cartItem.getQuantity());

            return productCartDTO;
        }).toList();

        double totalPrice = productCartDTOs.stream()
                .mapToDouble(productCartDTO -> productCartDTO.getPrice() * productCartDTO.getQuantity())
                .sum();

        fullCartInformationDTO.setCartId(cartId);
        fullCartInformationDTO.setProductInfo(productCartDTOs);
        fullCartInformationDTO.setTotalPrice(totalPrice);

        return fullCartInformationDTO;
    }

    @Override
    public CartDTO clearCart(UUID cartId) {

        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cartId not found with id " + cartId));

        List<CartItem> cartItems = cart.getCartItems();
        cartItems.clear();

        Cart emptiedCart = cartRepository.save(cart);

        return this.DTOConverter.convertCartToCartDTO(emptiedCart);
    }

    @Override
    public void deleteCart(UUID cartId) {

        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cartId not found with id " + cartId));
        cartRepository.delete(cart);
    }
}
