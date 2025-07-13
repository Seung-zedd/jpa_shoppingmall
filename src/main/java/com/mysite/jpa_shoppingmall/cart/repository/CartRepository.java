package com.mysite.jpa_shoppingmall.cart.repository;

import com.mysite.jpa_shoppingmall.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
