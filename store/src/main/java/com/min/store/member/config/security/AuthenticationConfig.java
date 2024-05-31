package com.min.store.member.config.security;
import com.min.store.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class AuthenticationConfig implements AuthenticationProvider {

    private final SystemUserDetails systemUserDetails;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String id = (String) token.getPrincipal();

        Member member = (Member) systemUserDetails.loadUserByUsername(id);

        if (!passwordEncoder().matches(authentication.getCredentials().toString(), member.encodePassword(member.getPassword()))){
            throw new BadCredentialsException("Bad Credential");
        }

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(member.getUsername(), null, null);
        result.setDetails(member);

        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
