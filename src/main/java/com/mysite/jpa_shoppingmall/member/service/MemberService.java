package com.mysite.jpa_shoppingmall.member.service;

import com.mysite.jpa_shoppingmall.member.entity.Member;
import com.mysite.jpa_shoppingmall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * Registers a new member after ensuring the email is not already in use.
     *
     * @param member the member entity to be saved
     * @return the persisted member entity
     * @throws IllegalStateException if a member with the same email already exists
     */
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    /**
     * Checks if a member with the same email already exists and throws an exception if a duplicate is found.
     *
     * @param member the member to validate for duplicate email registration
     * @throws IllegalStateException if a member with the same email is already registered
     */
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
