package com.store.memberapi.dto;

import com.store.memberapi.domain.MemberDomain;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {
    private Long id;
    private String email;
    private String name;

    public static MemberResponseDto from(MemberDomain memberDomain) {
        return MemberResponseDto.builder()
                .id(memberDomain.getId())
                .email(memberDomain.getEmail())
                .name(memberDomain.getName())
                .build();
    }
}
