package com.mysite.jpa_shoppingmall.item.mapper;

import com.mysite.jpa_shoppingmall.item.dto.ItemImgDto;
import com.mysite.jpa_shoppingmall.item.entity.ItemImg;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ItemImgMapper {

    // ✅ item 필드는 서비스 계층에서 직접 설정할 것이므로, 매핑에서 제외합니다.
    // ✅ id 필드 역시 DB에서 자동 생성되므로, 매핑에서 제외합니다.
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "id", ignore = true)
    ItemImg to(ItemImgDto itemImgDto);

    // ✅ (Entity -> DTO) 모든 필드 이름이 같으므로, 아무런 추가 설정이 필요 없습니다.
    ItemImgDto from(ItemImg itemImg);
}
