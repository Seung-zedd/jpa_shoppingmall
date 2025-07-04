package com.mysite.jpa_shoppingmall.member.entity;

import com.mysite.jpa_shoppingmall.member.constant.Role;
import com.mysite.jpa_shoppingmall.member.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

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
     * This constructor is intended for use by the builder pattern.
     *
     * @param id the unique identifier of the member
     * @param name the unique login ID of the member
     * @param email the unique email address of the member
     * @param password the encoded password of the member
     * @param address the address of the member
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
     * Creates a new Member entity from the provided form data, encoding the password and assigning the USER role.
     *
     * Returns {@code null} if the input DTO is {@code null}.
     *
     * @param memberFormDto the data transfer object containing member registration details
     * @param passwordEncoder the encoder used to securely hash the password
     * @return a new Member instance with encoded password and USER role, or {@code null} if the DTO is {@code null}
     */
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        if (memberFormDto == null) {
            return null;
        }

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
