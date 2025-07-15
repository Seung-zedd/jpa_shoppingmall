package com.mysite.jpa_shoppingmall.member.entity;

import com.mysite.jpa_shoppingmall.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
@Slf4j
class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username = "gildong", roles = "USER") // 얘가 지정한 사용자가 로그인한 상태라고 가정
    void auditingTest() {
        // given
        Member member = Member.builder()
                .build();
        memberRepository.save(member);

        em.flush();
        em.clear();

        // when
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(EntityNotFoundException::new);

        // then
        log.info("Register time: {}", findMember.getRegTime());
        log.info("Update time: {}", findMember.getUpdateTime());
        log.info("create member: {}", findMember.getCreatedBy());
        log.info("modify member: {}", findMember.getModifiedBy());

    }

}