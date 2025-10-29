package com.example.fanverse.mapper;

import com.example.fanverse.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberSqlMapper {

  void insertMember(Member member);
}
