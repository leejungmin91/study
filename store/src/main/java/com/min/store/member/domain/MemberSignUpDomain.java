package com.min.store.member.domain;

import lombok.*;

@Getter
public class MemberSignUpDomain {

    private final String email;
    private final String name;
    private final String password;

    @Builder
    public MemberSignUpDomain(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
