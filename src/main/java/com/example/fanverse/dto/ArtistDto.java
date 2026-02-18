package com.example.fanverse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {
  private Long id;

  @NotBlank(message = "아티스트명을 입력해 주세요.")
  private String artistName;

  @NotBlank(message = "아티스트명(영문)을 입력해 주세요.")
  private String artistNameEn;

  @NotBlank(message = "회사명을 입력해 주세요.")
  private String agency;

  private Long artistPopularity;
}
