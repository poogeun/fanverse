package com.example.fanverse.entity;

import com.example.fanverse.enums.Provider;
import com.example.fanverse.enums.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
  private Long id;
  private String name;
  private String email;
  private String password;
  private Provider provider;
  private Role role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Member(String name, String email, String password, Provider provider, Role role) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.provider = provider;
    this.role = role;
  }
}
