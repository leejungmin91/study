package com.min.store.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class MemberResponseDto {
    private Long id;
    private String email;
    private String name;
}
