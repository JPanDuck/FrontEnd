package com.example.realrealfinal.security.config;

import com.example.realrealfinal.security.CustomUserDetailsService;
import com.example.realrealfinal.security.jwt.JwtAuthenticationFilter;
import com.example.realrealfinal.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider tokenProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)    //폼 로그인,세션 비활성화 -> JWT 방식 활용
                .and()
                .cors(); //CORS 활성화 (AJAX 호출 시)

        //권한
        http.authorizeRequests()
                .antMatchers("/", "/index","/login", "/api/auth/login", "/auth/**",
                        "/css/**", "/js/**", "/images/**"
//                        "/error", "/error/**"
                ).permitAll()  //인증없이 접근가능
                .antMatchers("/admin/**", "/api/admin/**", "/auth/log-monitor").hasRole("ADMIN")  //ADMIN 권한만 가능
                //교수, 지도교수 권한 추가
                .anyRequest().authenticated();

        //JWT 필터 (스프링 기본 필터 이전에 추가)
        http.addFilterBefore(
                new JwtAuthenticationFilter(tokenProvider, userDetailsService),
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    // CORS 정책 세부 설정 - 추가
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 모든 도메인 허용 (*) -> 필요할 수 있음.
        configuration.addAllowedOriginPattern("*");

        // 허용할 메서드
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // 모든 헤더 허용
        configuration.addAllowedHeader("*");

        // 인증정보(JWT 쿠키, Authorization 헤더) 허용
        configuration.setAllowCredentials(true);

        // 프론트엔드에서 읽을 수 있도록 Authorization 헤더 노출
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
