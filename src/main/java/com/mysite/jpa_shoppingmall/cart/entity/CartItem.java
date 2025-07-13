package com.mysite.jpa_shoppingmall.cart.entity;

import com.mysite.jpa_shoppingmall.item.entity.Item;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PRIVATE)
//* 브릿지 엔티티 역할
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    @Builder
    private CartItem(Long id, Cart cart, Item item, int count) {
        this.id = id;
        this.cart = cart;
        this.item = item;
        this.count = count;
    }
}
