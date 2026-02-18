package com.example.fanverse.controller;

import com.example.fanverse.dto.ArtistDto;
import com.example.fanverse.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist")
public class ArtistController {

  @Autowired
  private ArtistService artistService;

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody ArtistDto artistDto) {
    artistService.create(artistDto);
    return ResponseEntity.ok(artistDto);
  }

}
