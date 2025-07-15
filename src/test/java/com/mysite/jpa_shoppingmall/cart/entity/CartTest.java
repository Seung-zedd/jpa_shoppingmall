package com.mysite.jpa_shoppingmall.cart.entity;

import com.mysite.jpa_shoppingmall.cart.repository.CartRepository;
import com.mysite.jpa_shoppingmall.member.dto.MemberFormDto;
import com.mysite.jpa_shoppingmall.member.entity.Member;
import com.mysite.jpa_shoppingmall.member.mapper.MemberMapper;
import com.mysite.jpa_shoppingmall.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
@Slf4j
class CartTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberMapper memberMapper;

    @PersistenceContext
    EntityManager em;

    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName("홍길동");
        memberFormDto.setEmail("tester@gmail.com");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword1("2345");
        return memberMapper.toMemberEntity(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    void findCartAndMemberTest() {
        // given
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = Cart.builder()
                .member(member)
                .build();
        cartRepository.save(cart);

        em.flush(); // 강제로 DB 반영
        em.clear(); // 영속성컨텍스트 초기화

        // when
        Cart savedCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);

        // then
        assertThat(savedCart.getMember().getId()).isEqualTo(member.getId());

    }
}