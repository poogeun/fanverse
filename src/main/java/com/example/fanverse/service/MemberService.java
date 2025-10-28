package com.example.fanverse.service;

import com.example.fanverse.dto.MemberDto;
import com.example.fanverse.mapper.MemberSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  @Autowired
  private MemberSqlMapper memberSqlMapper;

  public void insertMember(MemberDto memberDto) {
    memberSqlMapper.insertMember(memberDto);
  }
}
