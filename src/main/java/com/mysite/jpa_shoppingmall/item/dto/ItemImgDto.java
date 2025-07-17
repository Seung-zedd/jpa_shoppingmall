package com.mysite.jpa_shoppingmall.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemImgDto {

    private Long id; // Dto에 id가 필요한 이유는 DB와 유사하게 트랜잭션 연산이 필요하기 때문
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private boolean repImgYn;
}
