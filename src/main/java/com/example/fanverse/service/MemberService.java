package com.example.fanverse.service;

import com.example.fanverse.dto.member.MemberAuthenticationDto;
import com.example.fanverse.dto.member.MemberDto;
import com.example.fanverse.entity.Member;
import com.example.fanverse.enums.Provider;
import com.example.fanverse.enums.Role;
import com.example.fanverse.exception.member.DuplicateMemberException;
import com.example.fanverse.exception.member.MemberNotFoundException;
import com.example.fanverse.mapper.MemberSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {

  @Autowired
  private MemberSqlMapper memberSqlMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtService jwtService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var member = memberSqlMapper.findByEmail(email);

    if (member == null) {
      throw new MemberNotFoundException(email);
    }

    return member;
  }

  public void signUp(MemberDto memberDto) {
    var existsMember = memberSqlMapper.findByEmail(memberDto.getEmail());

    if (existsMember != null) {
      throw new DuplicateMemberException(memberDto.getEmail());
    }

    Member member = new Member(
            memberDto.getName(),
            memberDto.getEmail(),
            passwordEncoder.encode(memberDto.getPassword()),
            Provider.LOCAL,
            Role.USER
    );
    memberSqlMapper.insertMember(member);
  }

  public MemberAuthenticationDto login(String email, String password) {
    var member = memberSqlMapper.findByEmail(email);
    if (member == null) {
      throw new MemberNotFoundException(email);
    }

    if (passwordEncoder.matches(password, member.getPassword())) {
      var accessToken = jwtService.generateAccessToken(member);
      return new MemberAuthenticationDto(accessToken);
    } else {
      throw new MemberNotFoundException();
    }
  }
}
