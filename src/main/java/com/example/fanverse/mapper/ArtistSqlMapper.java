package com.example.fanverse.mapper;

import com.example.fanverse.dto.ArtistDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArtistSqlMapper {

  void insertArtist(ArtistDto artistDto);

  List<ArtistDto> selectAllArtists();

}
