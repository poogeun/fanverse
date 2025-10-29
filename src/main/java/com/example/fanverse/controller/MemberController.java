package com.example.fanverse.controller;

import com.example.fanverse.dto.MemberDto;
import com.example.fanverse.entity.Member;
import com.example.fanverse.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

  @Autowired
  MemberService memberService;

  @GetMapping("/signup")
  public String signup(Model model) {
    model.addAttribute("member", new MemberDto());
    return "member/signup";
  }

  @PostMapping("/signup")
  public String signup(@Valid MemberDto memberDto) {
    memberService.insertMember(memberDto);
    return "redirect:/";
  }
}
