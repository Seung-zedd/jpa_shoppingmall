package com.mysite.jpa_shoppingmall.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 인증되지 않은 모든 페이지의 요청을 허락
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 💡 new AntPathRequestMatcher() 제거
                        .requestMatchers("/**").permitAll()
                )
                .formLogin(formLogin -> formLogin.loginPage("/user/login")
                        .defaultSuccessUrl("/") // 로그인에 성공하면 루트로 리다이렉트
                )
                .logout(logout -> logout
                        // 💡 new AntPathRequestMatcher() 제거
                        .logoutUrl("/user/logout") // ✅ logoutUrl()을 사용하여 로그아웃 URL을 직접 지정
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
