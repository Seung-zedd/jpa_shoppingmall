package com.mysite.jpa_shoppingmall.member.service;

import com.mysite.jpa_shoppingmall.member.entity.Member;
import com.mysite.jpa_shoppingmall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * Loads user-specific data by email for authentication purposes.
     *
     * Retrieves a member by email from the repository. If the member exists, returns a Spring Security {@code User} object populated with the member's name, password, and role. Throws {@code UsernameNotFoundException} if no member is found with the given email.
     *
     * @param email the email address identifying the user whose data is required
     * @return a {@code UserDetails} object representing the authenticated user
     * @throws UsernameNotFoundException if no user is found with the specified email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getName())
                .password(member.getPassword())
                .roles(member.getRole().toString()) // Enum 타입이므로 String 타입으로 변환해줘야 SimpleGrantedAuthority가 동작함
                .build();
    }

    /**
     * Saves a new member after ensuring no existing member has the same email.
     *
     * @param member the member entity to be saved
     * @return the saved member entity
     * @throws IllegalStateException if a member with the same email already exists
     */
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    /**
     * Checks if a member with the same email already exists and throws an exception if a duplicate is found.
     *
     * @param member the member to check for duplication by email
     * @throws IllegalStateException if a member with the same email already exists
     */
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


}
