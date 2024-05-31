package com.min.store.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.min.store.member.dto.response.MemberResponseDto;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "MEMBER")
@Builder
@Entity
@IdClass(MemberId.class)
public class Member implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    @JsonIgnore
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
            Object details = authentication.getDetails();
            if(details instanceof Map map){
                Member member = Member.builder()
                        .id(Long.parseLong(String.valueOf(map.get("id"))))
                        .email((String) map.get("email"))
                        .name((String) map.get("name"))
                        .build();
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
