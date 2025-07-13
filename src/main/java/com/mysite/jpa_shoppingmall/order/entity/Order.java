package com.mysite.jpa_shoppingmall.order.entity;

import com.mysite.jpa_shoppingmall.member.entity.Member;
import com.mysite.jpa_shoppingmall.order.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PRIVATE)
@Table(name = "orders") // order 예약어가 있기 때문에 별도로 지정
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 주문 엔티티 기준에서 다대일 단방향 매핑(∵주문 객체한테 책임 위임)

    private LocalDateTime orderDate; // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //! 양방향 매핑은 연관 테이블과 많은 연관 관계를 매핑하기 때문에 필요할 때만 양방향 매핑을 추가할 것
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
