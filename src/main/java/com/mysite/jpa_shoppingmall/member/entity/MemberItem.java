package com.mysite.jpa_shoppingmall.member.entity;

import com.mysite.jpa_shoppingmall.item.entity.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PRIVATE)
//* 브릿지 엔티티 역할
//! 브릿지 엔티티라고 다 BaseEntity를 상속하는 것이 아니라, 비즈니스적으로 시간 데이터가 중요하다고 할 때 상속한다
public class MemberItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
