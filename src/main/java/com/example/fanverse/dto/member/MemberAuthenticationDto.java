package com.example.fanverse.dto.member;

import lombok.Data;

@Data
public class MemberAuthenticationDto {
  private String accessToken;

  public MemberAuthenticationDto(String accessToken) {
    this.accessToken = accessToken;
  }
}
