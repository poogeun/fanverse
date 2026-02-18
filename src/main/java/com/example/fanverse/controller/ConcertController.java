package com.example.fanverse.controller;

import com.example.fanverse.dto.ArtistDto;
import com.example.fanverse.dto.concert.ConcertDto;
import com.example.fanverse.dto.concert.ConcertFormDto;
import com.example.fanverse.service.ArtistService;
import com.example.fanverse.service.ConcertService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/concert")
public class ConcertController {

  @Autowired
  private ConcertService concertService;

  @Autowired
  private ArtistService artistService;

  @GetMapping
  public String concert(Model model) {
    List<ArtistDto> artistDtoList = artistService.findAllArtists();

    model.addAttribute("artistList", artistDtoList);
    model.addAttribute("artist", new ArtistDto());
    model.addAttribute("concert", new ConcertFormDto());
    return "concert/concert";
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> createConcert(@Valid @ModelAttribute ConcertFormDto concertFormDto)
      throws IOException {
    ConcertDto concertDto = concertService.create(concertFormDto);
    return ResponseEntity.ok(concertDto);
  }

}
