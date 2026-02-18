package com.example.fanverse.init;

import com.example.fanverse.entity.Member;
import com.example.fanverse.enums.Provider;
import com.example.fanverse.enums.Role;
import com.example.fanverse.mapper.MemberSqlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements ApplicationRunner {

  private final MemberSqlMapper memberSqlMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (memberSqlMapper.findByEmail("admin@test.com") != null) {
      return;
    }

    Member admin = new Member("관리자", "admin@test.com",
        passwordEncoder.encode("00000000"), Provider.LOCAL, Role.ADMIN);

    memberSqlMapper.insertMember(admin);
  }
}
