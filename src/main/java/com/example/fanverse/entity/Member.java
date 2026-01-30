package com.example.fanverse.entity;

import com.example.fanverse.enums.Provider;
import com.example.fanverse.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
public class Member implements UserDetails {
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

  public Member() {
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Member member = (Member) o;
    return Objects.equals(getId(), member.getId()) && Objects.equals(getName(), member.getName()) && Objects.equals(getEmail(), member.getEmail()) && Objects.equals(getPassword(), member.getPassword()) && getProvider() == member.getProvider() && getRole() == member.getRole() && Objects.equals(getCreatedAt(), member.getCreatedAt()) && Objects.equals(getUpdatedAt(), member.getUpdatedAt());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getEmail(), getPassword(), getProvider(), getRole(), getCreatedAt(), getUpdatedAt());
  }

  public static Member of(String email, String password) {
    var member = new Member();
    member.setEmail(email);
    member.setPassword(password);

    return member;
  }
}
