package com.min.store.member.domain;

import com.min.store.member.dto.response.MemberResponseDto;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "MEMBER")
@Builder
@Entity
@IdClass(MemberId.class)
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    public void updateMember(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public MemberResponseDto toMemberResponseDto(){
        return MemberResponseDto.builder()
                .id(id)
                .email(email)
                .name(name)
                .build();
    }

    public static Optional<Member> currentMember(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Member member) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
