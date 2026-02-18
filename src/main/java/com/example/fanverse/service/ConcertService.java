package com.example.fanverse.service;

import com.example.fanverse.dto.concert.ConcertDto;
import com.example.fanverse.dto.concert.ConcertFormDto;
import com.example.fanverse.mapper.ConcertSqlMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcertService {

  @Autowired
  private ConcertSqlMapper concertSqlMapper;

  @Autowired
  private FileService fileService;

  public ConcertDto create(ConcertFormDto concertFormDto) throws IOException {
    String imageUrl = fileService.save(concertFormDto.getPosterImg());

    ConcertDto concertDto = new ConcertDto();
    concertDto.setArtistId(concertFormDto.getArtistId());
    concertDto.setTitle(concertFormDto.getTitle());
    concertDto.setStartAt(concertFormDto.getStartAt());
    concertDto.setEndAt(concertFormDto.getEndAt());
    concertDto.setBookingUrl(concertFormDto.getBookingUrl());
    concertDto.setPosterImg(imageUrl);

    concertSqlMapper.insertConcert(concertDto);

    return concertDto;
  }

}
