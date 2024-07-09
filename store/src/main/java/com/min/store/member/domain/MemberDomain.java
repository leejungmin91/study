package com.min.store.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.min.store.order.domain.OrderDomain;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class MemberDomain implements UserDetails, Serializable {

    private final Long id;
    private final String email;
    private final String name;
    private final String password;
    private final transient List<OrderDomain> orders;

    @Builder
    public MemberDomain(@JsonProperty("id") Long id, @JsonProperty("email") String email, @JsonProperty("name") String name, @JsonProperty("password") String password, @JsonProperty("orders") List<OrderDomain> orders) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.orders = orders;
    }

    public static MemberDomain from(String email, String name, String password) {
        return MemberDomain.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
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
