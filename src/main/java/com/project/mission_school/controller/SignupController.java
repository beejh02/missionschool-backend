package com.project.mission_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.mission_school.dto.MemberDTO;
import com.project.mission_school.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {
    private final MemberService memberService;

    // 회원가입 페이지 이동
    @GetMapping("/member/signup")
    public String signupForm() {
        return "signup";
    }

    // 회원가입 처리
    @PostMapping("/member/signup")
    public String signup(@ModelAttribute MemberDTO memberDTO) {
        memberService.signup(memberDTO);
        return "login";
    }
}
