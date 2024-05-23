package kr.co.restfulapi.dto;

import kr.co.restfulapi.entity.Member;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "mbrNo")
@ToString
public class MemberDto {

    private Long mbrNo;

    private String email;

    private String name;

    public static MemberDto toDto(Member member){
        return MemberDto.builder()
                .mbrNo(member.getMbrNo())
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }
}
