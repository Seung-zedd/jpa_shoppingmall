package com.mysite.jpa_shoppingmall.item.entity;

import com.mysite.jpa_shoppingmall.item.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString // 디버깅 용도
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // DB가 변경되더라도 코드 수정 필요 x(but, dev와 prod DB가 다를 경우 예기치 못한 에러가 발생할 수 있음)
    private Long id; // 상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; // 상품명

    @Column(nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상태
    private LocalDateTime regTime; // 등록 시간
    private LocalDateTime updateTime; // 수정 시간

    /*
    실제로는 1개의 상품에 여러가지 옵션 및 옵션 상품의 가격, 재고, 배송 방법에 대한 정보까지 관리해야함
     */
}
