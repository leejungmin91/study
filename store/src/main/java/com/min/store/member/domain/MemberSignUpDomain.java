package com.min.store.member.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
public class MemberSignUpDomain {

    private final String email;
    private final String name;
    private final String password;

    @Builder
    public MemberSignUpDomain(@JsonProperty("email") String email, @JsonProperty("name") String name, @JsonProperty("password") String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
