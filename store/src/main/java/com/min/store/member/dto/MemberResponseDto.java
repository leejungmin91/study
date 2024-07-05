package com.min.store.member.dto;

import com.min.store.member.domain.MemberDomain;
import com.min.store.product.domain.ProductDomain;
import com.min.store.product.dto.ProductResponseDto;
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

    public static MemberResponseDto from(MemberDomain memberDomain) {
        return MemberResponseDto.builder()
                .id(memberDomain.getId())
                .email(memberDomain.getEmail())
                .name(memberDomain.getName())
                .build();
    }
}
