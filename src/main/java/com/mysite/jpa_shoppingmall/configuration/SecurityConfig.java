package com.mysite.jpa_shoppingmall.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
         * Configures the application's security filter chain, permitting all requests, setting up custom login and logout endpoints, and enabling CSRF protection.
         *
         * @param http the {@link HttpSecurity} to modify
         * @return the configured {@link SecurityFilterChain}
         * @throws Exception if an error occurs during configuration
         */
        @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. 인증되지 않은 모든 페이지의 요청을 허락 (최신 스타일)
                .authorizeHttpRequests(authorize -> authorize
                        //! 프로덕트 환경에서는 보안상 위험할 수 있기 때문에 필요한 엔드포인트만 허용하도록 한다.
                        .requestMatchers("/", "/members/new", "/members/login", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
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
                // 4. 💡 CSRF 설정을 명시적으로 활성화하여 타이밍 문제를 해결
                .csrf(withDefaults());

        return http.build();
    }

    /**
     * Provides a {@link PasswordEncoder} bean that uses the BCrypt hashing algorithm for encoding passwords.
     *
     * @return a BCrypt-based password encoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides the {@link AuthenticationManager} bean by retrieving it from the given {@link AuthenticationConfiguration}.
     *
     * @param authenticationConfiguration the authentication configuration used to obtain the authentication manager
     * @return the configured {@link AuthenticationManager}
     * @throws Exception if the authentication manager cannot be obtained
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
