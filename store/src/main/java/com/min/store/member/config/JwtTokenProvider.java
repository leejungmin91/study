package com.min.store.member.config;

import com.min.store.member.domain.Member;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${spring.security.jwt.secret}")
    private String jwtSecret;

    @Value("${spring.security.jwt.token-validity-in-seconds}")
    private long tokenValidTime;

    @Value("${spring.security.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenValidTime;

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 토큰 생성
    public String createToken(Authentication authentication) {

        Claims claims = Jwts.claims().setSubject(authentication.getName()); // JWT payload 에 저장되는 정보단위

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .claim("member", authentication.getDetails())
                .setIssuedAt(new Date()) // 토큰 발행 시간 정보
                .setExpiration(new Date(new Date().getTime() + (tokenValidTime * 1000))) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, jwtSecret)  // 사용할 암호화 알고리즘
                .compact();
    }

    /**
     * Refresh 토큰 생성
     */
    public String createRefreshToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName());

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshTokenValidTime * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        Object member = claims.get("member");

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(claims.getSubject(), "", null);
        result.setDetails(member);

        return result;
    }

    // Request의 Header에서 token 값을 가져옵니다.
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        if(request.getCookies() == null) throw new JwtException("refreshToken 이 존재하지 않습니다.");
        // ApiException 을 상위에서 처리
        String bearerToken = Arrays.stream(request.getCookies())
                .filter(c -> "refreshToken".equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new JwtException("refreshToken 이 존재하지 않습니다."));

        if (StringUtils.hasText(bearerToken)) {
            return bearerToken;
        }
        return null;
    }

    // 토큰의 유효성 + 만료일자 확인
    public void validateToken(String jwtToken) throws JwtException{
        Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
        log.info("토큰의 만료일자 --> {}", claims.getBody().getExpiration());
    }

    public String reissueToken(HttpServletRequest request) {

        String refreshToken = this.resolveRefreshToken(request);
        this.validateToken(refreshToken);

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) this.getAuthentication(refreshToken);
        String email = token.getPrincipal().toString();

        UsernamePasswordAuthenticationToken newAuthToken = new UsernamePasswordAuthenticationToken(email, null, null);

        // 새로운 JWT 발급
        String newToken = this.createToken(newAuthToken);
        SecurityContextHolder.getContext().setAuthentication(newAuthToken);

        return newToken;
    }

}
