package com.example.fanverse.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

		private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

		private static final SecretKey key = Jwts.SIG.HS256.key().build();

		// username으로 token 만들기
		public String generateAccessToken(UserDetails userDetails) {
				return generateToken(userDetails.getUsername());
		}

		// token으로부터 username 추출
		public String getUsername(String accessToken) {
				return getSubject(accessToken);
		}

		// jwt 토큰을 만드는 함수
		private String generateToken(String subject) {
				var now = new Date();
				var exp = new Date(now.getTime() + 1000 * 60 * 60 * 3);

				return Jwts.builder().subject(subject).signWith(key).issuedAt(now).expiration(exp).compact();
		}

		// jwt를 전달받아서 subject를 추출해내는 함수
		private String getSubject(String token) {
				try {
					return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
				} catch (JwtException e) {
						logger.error("JwtException", e);
					throw e;
				}
		}
}
