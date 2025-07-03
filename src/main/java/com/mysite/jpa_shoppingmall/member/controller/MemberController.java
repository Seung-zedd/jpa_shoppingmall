package com.mysite.jpa_shoppingmall.member.controller;

import com.mysite.jpa_shoppingmall.member.dto.MemberFormDto;
import com.mysite.jpa_shoppingmall.member.entity.Member;
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

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Handles GET requests to display the member registration form.
     *
     * Adds an empty MemberFormDto to the model for form binding and returns the view name for the registration form.
     *
     * @return the view name for the member registration form
     */
    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "/member/memberForm";
    }

    /**
     * Processes member registration form submissions, validates input, and creates a new member account.
     *
     * If validation errors are present, returns the registration form view with errors displayed.
     * On successful registration, redirects to the home page.
     * If an `IllegalStateException` occurs during member creation or saving, adds the error message as a flash attribute and returns the registration form view.
     *
     * @param memberFormDto the submitted member registration form data
     * @param bindingResult holds validation results for the form data
     * @return the view name to render or a redirect instruction
     */
    @PostMapping("/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
        // Check password confirmation
        if (!memberFormDto.isPasswordConfirmed()) {
            bindingResult.rejectValue("password2", "passwordMismatch", "비밀번호가 일치하지 않습니다.");
        }
        // 에러 검증
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }
        // 서비스 로직 실행
        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
            log.info("after creating member: {}", member.toString());
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

}
