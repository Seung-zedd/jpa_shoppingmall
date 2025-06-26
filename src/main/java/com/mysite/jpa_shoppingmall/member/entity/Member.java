package com.mysite.jpa_shoppingmall.member.entity;

import com.mysite.jpa_shoppingmall.member.constant.Role;
import com.mysite.jpa_shoppingmall.member.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username; // 사용자 로그인 아이디

    @Column(unique = true)
    private String email;
    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    private Member(Long id, String username, String email, String password, String address, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        if (memberFormDto == null) {
            return null;
        }

        return Member.builder()
                .username(memberFormDto.getUsername())
                .email(memberFormDto.getEmail())
                .password(memberFormDto.getPassword())
                .address(memberFormDto.getAddress())
                .role(Role.USER)
                .build();
    }
}
