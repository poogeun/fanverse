package com.example.fanverse.dto.concert;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ConcertFormDto {

  @NotNull(message = "아티스트를 선택해 주세요.")
  private Long artistId;

  @NotBlank(message = "공연명을 입력해 주세요.")
  private String title;

  @NotNull(message = "시작일을 입력해 주세요.")
  private LocalDateTime startAt;

  @NotNull(message = "종료일을 입력해 주세요.")
  private LocalDateTime endAt;

  @NotBlank(message = "장소를 입력해 주세요.")
  private String place;

  private String bookingUrl;

  private MultipartFile posterImg;
}
