package com.mysite.jpa_shoppingmall.item.entity;

import com.mysite.jpa_shoppingmall.config.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ItemImg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName; // 이미지 파일명
    private String oriImgName; // 원본 이미지 파일명
    private String imgUrl; // 이미지 조회 경로
    private boolean repImgYn; // 대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY) // 하나의 아이템에 여러 개의 이미지가 있을 수 있으므로
    @JoinColumn(name = "item_id")
    private Item item;

    //* 이미지 정보를 업데이트하는 메소드
    public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
