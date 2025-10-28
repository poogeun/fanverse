package com.example.fanverse.mapper;

import com.example.fanverse.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberSqlMapper {

  void insertMember(MemberDto memberDto);
}
