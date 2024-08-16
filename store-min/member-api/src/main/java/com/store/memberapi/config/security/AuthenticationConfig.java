package com.store.memberapi.config.security;

import com.store.memberapi.domain.MemberDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class AuthenticationConfig implements AuthenticationProvider {

    private final SystemUserDetails systemUserDetails;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String id = (String) token.getPrincipal();

        MemberDomain memberDomain = (MemberDomain) systemUserDetails.loadUserByUsername(id);

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), memberDomain.getPassword())){
            throw new BadCredentialsException("Bad Credential");
        }

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(memberDomain.getEmail(), null, null);
        result.setDetails(memberDomain);

        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
