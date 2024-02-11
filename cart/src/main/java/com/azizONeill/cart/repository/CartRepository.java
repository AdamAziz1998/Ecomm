package com.azizONeill.cart.repository;

import com.azizONeill.cart.model.Cart;
import com.azizONeill.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    Cart findByUserId(UUID userId);

    void deleteByUserId(UUID userId);

    @Modifying
    @Query("UPDATE Cart c SET c.cartItems = :cartItems WHERE c.id = :cartId")
    void addProductToCart(@Param("cartId") UUID cartId, @Param("cartItems") List<CartItem> cartItems);

    @Modifying
    @Query("UPDATE CartItem ci SET ci.quantity = :quantity WHERE ci.id = :cartItemId")
    void updateCartItemQuantity(@Param("cartItemId") UUID cartItemId, @Param("quantity") int quantity);

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.id = :cartItemId")
    void removeProductFromCart(@Param("cartItemId") UUID cartItemId);
}
