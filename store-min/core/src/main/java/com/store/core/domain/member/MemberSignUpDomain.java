package com.store.core.domain.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
