package com.example.bootheat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ✅ CSRF 비활성화 (REST API에선 보통 끔)
                .csrf(csrf -> csrf.disable())

                // ✅ 필요하다면 CORS도 Security 차원에서 허용
                .cors(cors -> {})

                // ✅ 요청 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/orders").permitAll()
                        .requestMatchers("/api/**").permitAll()   // 모든 API 허용 (포트폴리오용)
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
