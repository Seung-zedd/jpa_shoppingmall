package com.mysite.jpa_shoppingmall.order.entity;

import com.mysite.jpa_shoppingmall.item.entity.Item;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PRIVATE)
//* 브릿지 엔티티 역할
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice; // 주문가격
    private int count; // 수량
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    /*@Builder
    private OrderItem(Long id, Order order, Item item) {
        this.id = id;
        this.order = order;
        this.item = item;
    }*/
}
