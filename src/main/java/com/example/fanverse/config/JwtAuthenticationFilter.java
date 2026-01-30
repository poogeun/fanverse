package com.example.fanverse.config;

import com.example.fanverse.exception.jwt.JwtTokenNotFoundException;
import com.example.fanverse.service.JwtService;
import com.example.fanverse.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

		@Autowired private JwtService jwtService;
		@Autowired private MemberService memberService;

		@Override
		protected void doFilterInternal(
						HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
						throws ServletException, IOException {
				// Todo: JWT 기반 검증
				String BEARER_PREFIX = "Bearer ";
				var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
				var securityContext = SecurityContextHolder.getContext();

				if(ObjectUtils.isEmpty(authorization) || !authorization.startsWith(BEARER_PREFIX)) {
//					throw new JwtTokenNotFoundException();
					filterChain.doFilter(request, response);
					return;
				}

				if (!ObjectUtils.isEmpty(authorization) && authorization.startsWith(BEARER_PREFIX)
				&& securityContext.getAuthentication() == null) {

						var accessToken = authorization.substring(BEARER_PREFIX.length());
						var username = jwtService.getUsername(accessToken);
						var userDetails = memberService.loadUserByUsername(username);

						// ===== 여기까지 검증이 완료되었으면 =====

						// 사용자 인증 정보가 담긴 토큰 생성
						var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						// 토큰에 현재 http 정보 담기
						authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						// 시큐리티컨텍스트에 토큰 인증정보 세팅
						securityContext.setAuthentication(authenticationToken);
						// 해당 시큐리티컨텍스트 저장
						SecurityContextHolder.setContext(securityContext);

				}
				filterChain.doFilter(request, response);

		}
}
