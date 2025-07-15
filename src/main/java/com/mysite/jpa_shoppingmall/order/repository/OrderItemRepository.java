package com.mysite.jpa_shoppingmall.order.repository;

import com.mysite.jpa_shoppingmall.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
