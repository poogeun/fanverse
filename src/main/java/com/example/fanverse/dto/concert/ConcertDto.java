package com.example.fanverse.dto.concert;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConcertDto {
  private Long id;

  private Long artistId;

  private String title;

  private LocalDateTime startAt;

  private LocalDateTime endAt;

  private String place;

  private String bookingUrl;

  private String posterImg;

}
