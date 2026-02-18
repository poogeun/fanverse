package com.example.fanverse.mapper;

import com.example.fanverse.dto.concert.ConcertDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConcertSqlMapper {

  void insertConcert(ConcertDto concertDto);

  ConcertDto findById(Long id);

}
