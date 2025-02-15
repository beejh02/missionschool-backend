package com.project.mission_school.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.mission_school.dto.MemberDTO;
import com.project.mission_school.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원 목록 조회 (로그인 후에만 가능)
    @GetMapping("/member/")
    public String findAll(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            // 로그인 안된 경우, 로그인 페이지로 리다이렉트
            return "redirect:/member/login";
        }

        // 로그인 후 회원 목록 조회
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "list";  // 목록 페이지
    }

    // 회원 상세 조회
    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";  // 상세 페이지
    }

    // 내 정보 수정 페이지 이동
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    // 회원 정보 수정 처리
    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();  // 수정 후 상세페이지로 리다이렉트
    }

    // 회원 삭제
    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/";  // 삭제 후 회원 목록 페이지로 리다이렉트
    }
}
