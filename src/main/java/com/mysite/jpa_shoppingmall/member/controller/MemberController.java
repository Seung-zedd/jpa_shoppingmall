package com.mysite.jpa_shoppingmall.member.controller;

import com.mysite.jpa_shoppingmall.member.dto.MemberFormDto;
import com.mysite.jpa_shoppingmall.member.entity.Member;
import com.mysite.jpa_shoppingmall.member.mapper.MemberMapper;
import com.mysite.jpa_shoppingmall.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;

    //* 회원가입 폼 조회
    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    //* 사용자가 회원가입 데이터를 서버한테 요청하는 메서드
    @PostMapping("/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // 에러 검증
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        // 서비스 로직 실행
        try {
            //* 컨트롤러 계층의 흐름 제어 + 객체 생성 책임 -> 흐름 제어
            Member member = memberMapper.toMemberEntity(memberFormDto, passwordEncoder);
//            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
            log.info("after creating member: {}", member.toString());
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/members/new";
        }

        return "redirect:/";

    }

    @GetMapping("/login")
    public String loginMember(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        }
        return "member/memberLoginForm";
    }

}
