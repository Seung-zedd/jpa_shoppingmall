package com.mysite.jpa_shoppingmall.order.repository;

import com.mysite.jpa_shoppingmall.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
