package com.example.fanverse.controller;

import com.example.fanverse.dto.member.MemberDto;
import com.example.fanverse.exception.member.DuplicateMemberException;
import com.example.fanverse.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
  public String signup(@Valid @ModelAttribute("member") MemberDto memberDto,
      BindingResult bindingResult) {
    try {
      memberService.signUp(memberDto);
    } catch (DuplicateMemberException e) {
      bindingResult.rejectValue(
          "email", "duplicate", "이미 사용 중인 이메일입니다."
      );
    }

    if (bindingResult.hasErrors()) {
      return "member/signup";
    }

    return "redirect:/";
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("member", new MemberDto());
    return "member/login";
  }

  @PostMapping("/login")
  public String login(@Valid MemberDto memberDto, HttpServletResponse res) {
    var authDto = memberService.login(memberDto.getEmail(), memberDto.getPassword());

    ResponseCookie cookie = ResponseCookie.from("access_token", authDto.getAccessToken())
        .httpOnly(true)
        .path("/")
        .maxAge(Duration.ofMinutes(30))
        .secure(false)
        .sameSite("Lax")
        .build();

    res.addHeader("Set-Cookie", cookie.toString());

    return "redirect:/";
  }

  @PostMapping("/logout")
  public String logout(HttpServletResponse res) {
    ResponseCookie cookie = ResponseCookie.from("access_token", "")
        .path("/")
        .maxAge(0)
        .httpOnly(true)
        .secure(false)
        .sameSite("Lax")
        .build();

    res.addHeader("Set-Cookie", cookie.toString());
    return "redirect:/";
  }
}
