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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
     * Adds an empty MemberFormDto to the model for form binding and returns the view name for the registration page.
     *
     * @param model the model to which the form DTO is added
     * @return the view name for the member registration form
     */
    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    /**
     * Processes member registration form submissions, creating a new member if validation passes.
     *
     * If the submitted form contains validation errors, redisplays the registration form. On successful member creation, redirects to the home page. If an error occurs during member creation or saving, adds an error message and redisplays the registration form.
     *
     * @param memberFormDto the submitted member registration data
     * @param bindingResult the result of form validation
     * @param redirectAttributes attributes for flash messages during redirection
     * @return the view name to render or a redirect instruction
     */
    @PostMapping("/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
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
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";

    }

    /**
     * Handles GET requests to display the member login form.
     *
     * Adds a login error message to the model and returns the view for the member login page.
     *
     * @return the name of the login form view
     */
    @GetMapping("/login")
    public String loginMember(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/memberLoginForm";
    }

}
