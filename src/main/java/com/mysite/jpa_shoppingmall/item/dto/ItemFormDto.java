package com.mysite.jpa_shoppingmall.item.dto;

import com.mysite.jpa_shoppingmall.item.constant.ItemSellStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {

    private Long id;

    //! String 타입에만 @NotBlank를 사용한다(∵공백도 아스키코드값이 있기 때문)
    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    //! 반면에 숫자는 @NotNull을 사용
    //? int 대신 Integer 타입을 사용한 이유: 사용자가 가격을 입력하지 않았을 때, Integer는 객체이므로 null 상태를 가질 수 있는데 이를 Validation 검사에서 처리할 수 있음
    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "상품명은 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    // 상품 저장 후 수정할 때 상품 이미지 정보를 저장하는 리스트
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    // 상품의 이미지 아이디를 저장
    private List<Long> itemImgIds = new ArrayList<>();


}
