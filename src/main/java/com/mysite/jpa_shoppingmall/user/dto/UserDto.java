package com.mysite.jpa_shoppingmall.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString // 디버깅 또는 로깅용
public class UserDto {
    private String name;
    private int age;
}
