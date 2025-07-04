package com.mysite.jpa_shoppingmall.member.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$",
            message = ".com으로 끝나는 이메일만 허용됩니다."
    )
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password1;

    @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
    private String password2;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;

}
