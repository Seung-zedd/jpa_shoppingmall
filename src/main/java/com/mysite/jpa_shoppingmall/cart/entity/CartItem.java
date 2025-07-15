package com.mysite.jpa_shoppingmall.cart.entity;

import com.mysite.jpa_shoppingmall.config.audit.BaseEntity;
import com.mysite.jpa_shoppingmall.item.entity.Item;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
//* 브릿지 엔티티 역할
//? "고객이 언제 담았고, 언제 수량을 바꿨는가?"와 같은 시간의 흐름이 중요한 비즈니스 데이터가 될 수 있어서 Audit 기능이 있는 BaseEntity를 상속
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
}
