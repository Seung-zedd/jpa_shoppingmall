package com.mysite.jpa_shoppingmall.item.mapper;

import com.mysite.jpa_shoppingmall.item.dto.ItemFormDto;
import com.mysite.jpa_shoppingmall.item.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ItemMapper {

    @Mapping(target = "id", ignore = true)
    Item to(ItemFormDto itemFormDto);
    ItemFormDto from(Item item);
}
