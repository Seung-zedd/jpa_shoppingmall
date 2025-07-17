package com.mysite.jpa_shoppingmall.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. 인증되지 않은 모든 페이지의 요청을 허락 (최신 스타일)
                .authorizeHttpRequests(authorize -> authorize
                                //! 프로덕트 환경에서는 보안상 위험할 수 있기 때문에 필요한 엔드포인트만 허용하도록 한다.
//                        .requestMatchers("/", "/members/new", "/members/login", "/css/**", "/js/**", "/images/**").permitAll()
                                //* 이건 편의상 사용
//                                .requestMatchers("/**").permitAll()

                                //* static 디렉토리의 하위 파일은 인증을 무시
                                // permitAll()을 통해 모든 사용자가 인증없이 해당 경로에 접근할 수 있도록 설정
                                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                                .requestMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()

                                //* /admin으로 시작하는 경로는 해당 계정이 ADMIN Role일 경우에나 접근 가능
                                .requestMatchers("/admin/**").hasRole("ADMIN")

                                //* 위에서 설정해준 경로를 제외한 나머지 경로들은 모두 인증을 요구하도록 설정
                                .anyRequest()
                                .authenticated()
                )
                // 2. 로그인 설정
                .formLogin(formLogin -> formLogin.loginPage("/members/login")
                        .defaultSuccessUrl("/")
                        .usernameParameter("email")
                )
                // 3. 로그아웃 설정 (최신 스타일)
                .logout(logout -> logout
                        .logoutUrl("/members/logout") // ✅ .logoutUrl() 메서드로 간단하게 교체
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                )
                .exceptionHandling(e -> e
                        // ✅ 필드 주입 대신, 아래에 있는 Bean 메서드를 직접 호출!
                        .authenticationEntryPoint(customAuthenticationEntryPoint())
                )
                // 4. 💡 CSRF 설정을 명시적으로 활성화하여 타이밍 문제를 해결
                .csrf(withDefaults());

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

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }
}
