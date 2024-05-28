package com.min.store.member.config.security;

import com.min.store.member.config.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Slf4j
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨.
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final AuthenticationEntryPoint entryPoint;
    private final AuthenticationConfig authenticationConfig;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationConfig);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headerConfig ->
                        headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                /* Jwt 토큰 사용 시 필수
                 * security는 기본적으로 session 기반으로 동작하는데 STATELESS 를 설정하지 않으면 인증정보를 session에 저장해 새로이 인증하지 않음.
                 */
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(request ->
                        request
                                .antMatchers("/sample/**", "/h2-console/**", "/member/signup").permitAll()
                                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .antMatchers(HttpMethod.POST, "/auth/reissue").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(handler -> handler.authenticationEntryPoint(entryPoint))
                .formLogin().disable()
                .httpBasic().disable()
                .build();
    }
}
