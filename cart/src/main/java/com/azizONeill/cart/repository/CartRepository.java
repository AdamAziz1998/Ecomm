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
}
