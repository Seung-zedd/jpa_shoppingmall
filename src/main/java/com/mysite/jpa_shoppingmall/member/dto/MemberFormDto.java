package com.mysite.jpa_shoppingmall.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {

    private String username;
    private String email;
    private String password;
    private String address;

}
