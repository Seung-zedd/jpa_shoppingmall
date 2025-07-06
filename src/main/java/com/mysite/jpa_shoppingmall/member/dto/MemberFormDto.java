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
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password1;

    @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
    private String password2;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;

    // @AssertTrue가 붙은 메서드를 @Valid 어노테이션을 처리할 때 자동으로 실행
    @AssertTrue(message = "비밀번호가 일치하지 않습니다.")
    public boolean isPasswordMatching() {
        return password1 != null && password1.equals(password2);
    }
}
