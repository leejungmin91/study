package com.min.store.member.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginRequestDto {

    @NotNull(message = "이메일주소는 필수입니다.")
    private String email;

    @NotNull(message = "비밀번호는 필수입니다.")
    private String password;
}
