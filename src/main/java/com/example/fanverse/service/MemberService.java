package com.example.fanverse.service;

import com.example.fanverse.dto.MemberDto;
import com.example.fanverse.entity.Member;
import com.example.fanverse.enums.Provider;
import com.example.fanverse.enums.Role;
import com.example.fanverse.mapper.MemberSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  @Autowired
  private MemberSqlMapper memberSqlMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public void insertMember(MemberDto memberDto) {
    Member member = new Member(
            memberDto.getName(),
            memberDto.getEmail(),
            passwordEncoder.encode(memberDto.getPassword()),
            Provider.LOCAL,
            Role.USER
    );
    memberSqlMapper.insertMember(member);
  }
}
