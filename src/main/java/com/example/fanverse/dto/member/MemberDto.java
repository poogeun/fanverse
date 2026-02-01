package com.example.fanverse.dto.member;

import com.example.fanverse.entity.Member;
import com.example.fanverse.enums.Provider;
import com.example.fanverse.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

  private Long id;

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

  public static MemberDto from(Member member) {
    return new MemberDto(
            member.getId(),
            member.getName(),
            member.getEmail(),
            member.getPassword(),
            member.getProvider(),
            member.getRole(),
            member.getCreatedAt(),
            member.getUpdatedAt());
  }
}
