package com.mysite.jpa_shoppingmall.member.service;

import com.mysite.jpa_shoppingmall.member.dto.MemberFormDto;
import com.mysite.jpa_shoppingmall.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
@Slf4j
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword("2345");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원강비 테스트")
    void saveMemberTest() {
        // given
        // 1. 테스트 메서드 안에서 원본 DTO를 직접 생성합니다.
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword("2345"); // ✨ 우리가 검증에 사용할 '원본 평문'

        // 2. 이 DTO를 사용하여 Member 엔티티를 생성합니다. (이 안에서 암호화가 일어남)
        Member member = Member.createMember(memberFormDto, passwordEncoder);

        // when
        Member savedMember = memberService.saveMember(member);

        // then
        // 3. 다른 필드들은 직접 비교합니다.
        assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(savedMember.getName()).isEqualTo(member.getName());
        assertThat(savedMember.getAddress()).isEqualTo(member.getAddress());
        assertThat(savedMember.getRole()).isEqualTo(member.getRole());

        // 4. ✅ 비밀번호는 "원본 평문"과 "저장된 암호화된 값"을 비교합니다.
        assertThat(passwordEncoder.matches(memberFormDto.getPassword(), savedMember.getPassword())).isTrue();
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    void saveDuplicateMemberTest() {
        // given
        Member member1 = createMember();
        Member member2 = createMember();


        // when
        memberService.saveMember(member1);
        Throwable e = assertThrows(IllegalStateException.class, () -> memberService.saveMember(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 가입된 회원입니다.");

    }


}