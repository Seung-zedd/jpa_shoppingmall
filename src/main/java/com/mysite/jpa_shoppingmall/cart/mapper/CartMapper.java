package com.mysite.jpa_shoppingmall.cart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

}
