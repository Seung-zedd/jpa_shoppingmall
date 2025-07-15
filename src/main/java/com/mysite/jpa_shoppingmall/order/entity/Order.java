package com.mysite.jpa_shoppingmall.order.entity;

import com.mysite.jpa_shoppingmall.member.entity.Member;
import com.mysite.jpa_shoppingmall.order.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders") // order 예약어가 있기 때문에 별도로 지정
// 클래스 레벨에 빌더를 선언함으로써 builder() 부분에서 함수 오버로딩을 통해 원하는 필드만 설정할 수 있음
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 주문 엔티티 기준에서 다대일 단방향 매핑(∵주문 객체한테 책임 위임)

    private LocalDateTime orderDate; // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //! 양방향 매핑은 연관 테이블과 많은 연관 관계를 매핑하기 때문에 필요할 때만 양방향 매핑을 추가할 것
    //! 고아 객체 제거 옵션은 반드시 참조하는 부모 엔티티가 하나일 때만 사용할 것
    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
