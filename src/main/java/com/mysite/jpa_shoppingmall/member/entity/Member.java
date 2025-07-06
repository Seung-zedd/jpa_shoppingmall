package com.mysite.jpa_shoppingmall.member.entity;

import com.mysite.jpa_shoppingmall.member.constant.Role;
import com.mysite.jpa_shoppingmall.member.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name; // 사용자 로그인 아이디

    @Column(unique = true)
    private String email;
    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Constructs a Member instance with the specified id, name, email, password, address, and role.
     *
     * This constructor is intended for use by the builder and within the class only.
     *
     * @param id the unique identifier of the member
     * @param name the username for login
     * @param email the member's unique email address
     * @param password the member's encoded password
     * @param address the member's address
     * @param role the role assigned to the member
     */
    @Builder
    private Member(Long id, String name, String email, String password, String address, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    /**
     * Creates a new Member entity from the provided MemberFormDto, encoding the password and assigning the default user role.
     *
     * @param memberFormDto the data transfer object containing member registration details; if null, returns null
     * @param passwordEncoder the encoder used to securely hash the member's password
     * @return a new Member instance with encoded password and user role, or null if the input DTO is null
     */
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        // null 체크를 명시적으로 수행하고, null일 경우 명확한 예외 메시지와 함께 예외 발생
        Objects.requireNonNull(memberFormDto, "memberFormDto cannot be null");
        Objects.requireNonNull(passwordEncoder, "passwordEncoder cannot be null");

        String encodedPassword = passwordEncoder.encode(memberFormDto.getPassword1());

        return Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                // ✅ 올바른 방법: DTO의 비밀번호를 인코더로 암호화하여 저장
                .password(encodedPassword)
                .address(memberFormDto.getAddress())
                .role(Role.USER)
                .build();
    }
}
