package com.example.fanverse.controller;

import com.example.fanverse.dto.MemberDto;
import com.example.fanverse.entity.Member;
import com.example.fanverse.exception.member.DuplicateMemberException;
import com.example.fanverse.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
  public String signup(@Valid MemberDto memberDto, BindingResult bindingResult) {
    try{
      memberService.signUp(memberDto);
    } catch (DuplicateMemberException e) {
      bindingResult.rejectValue(
          "email", "duplicate", "이미 사용 중인 이메일입니다."
      );
    }

    if(bindingResult.hasErrors()) {
      return "member/signup";
    }

    return "redirect:/";
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("member", new MemberDto());
    return "member/login";
  }

//  @PostMapping("/login")
//  public String login(@Valid MemberDto memberDto) {
//
//  }
}
