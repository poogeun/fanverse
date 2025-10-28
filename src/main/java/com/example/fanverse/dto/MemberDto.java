package com.example.fanverse.dto;

import com.example.fanverse.enums.Provider;
import com.example.fanverse.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {

  private Long id;

  @NotBlank(message = "이름을 입력해주세요.")
  private String name;

  @NotBlank(message = "이메일을 입력해 주세요.")
  @Email(message = "올바른 이메일 형식이 아닙니다.")
  private String email;

  @NotBlank(message = "비밀번호를 입력해 주세요.")
  private String password;

  private Provider provider;

  private Role role;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
