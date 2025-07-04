package com.mysite.jpa_shoppingmall.member.repository;

import com.mysite.jpa_shoppingmall.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
