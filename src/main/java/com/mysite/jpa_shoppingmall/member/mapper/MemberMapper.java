package com.mysite.jpa_shoppingmall.member.mapper;

import com.mysite.jpa_shoppingmall.member.dto.MemberFormDto;
import com.mysite.jpa_shoppingmall.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

//* 빌드 시 매핑 안된 필드를 로그로 찾기 위해 WARN으로 설정하는 것이 좋음
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MemberMapper {

    //! INSTANCE로 직접 Mapper 구현체 인스턴스를 가져오면 build 패키지에서 duplicated class 문제가 발생

    //* 필드 이름이 같으면 자동으로 매핑해줌
    @Mappings({
            // 1. id 필드는 DTO에 없으므로 무시. (DB에서 자동 생성)
            @Mapping(target = "id", ignore = true),

            // 2. password 필드는 암호화가 필요하므로, expression을 사용해 직접 로직을 주입.
            // "java(...)" 안에는 일반 자바 코드를 쓸 수 있습니다.
            @Mapping(target = "password", expression = "java(passwordEncoder.encode(memberFormDto.getPassword1()))"),

            // 3. role 필드는 DTO에 없으므로, 기본값(USER)을 상수로 지정.
            @Mapping(target = "role", constant = "USER")
    })
    // 💡 PasswordEncoder를 파라미터로 받아와 expression에서 사용할 수 있습니다.
    Member toMemberEntity(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder);
}
