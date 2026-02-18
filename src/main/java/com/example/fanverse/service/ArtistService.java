package com.example.fanverse.service;

import com.example.fanverse.dto.ArtistDto;
import com.example.fanverse.mapper.ArtistSqlMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

  @Autowired
  private ArtistSqlMapper artistSqlMapper;

  public void create(ArtistDto artistDto) {
    artistSqlMapper.insertArtist(artistDto);
  }

  public List<ArtistDto> findAllArtists() {
    return artistSqlMapper.selectAllArtists();
  }

}
