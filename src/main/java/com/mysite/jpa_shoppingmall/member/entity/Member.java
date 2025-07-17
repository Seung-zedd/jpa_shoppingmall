package com.mysite.jpa_shoppingmall.member.entity;

import com.mysite.jpa_shoppingmall.config.audit.BaseEntity;
import com.mysite.jpa_shoppingmall.member.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
//* 1. JPA를 위해 기본 생성자는 필요하지만, 외부에서 함부로 쓰지 못하게 protected로 제한합니다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id") // 직접 명명해줘야 id -> member_id로 나옴
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name; // 사용자 로그인 아이디

    @Column(unique = true)
    private String email;
    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    @Setter
    private Role role;
}
