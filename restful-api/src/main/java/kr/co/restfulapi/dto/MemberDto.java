package kr.co.restfulapi.dto;

import kr.co.restfulapi.entity.Member;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "mbrNo")
@ToString
public class MemberDto {

    private Long mbrNo;

    private String id;

    private String name;

    public static MemberDto toDto(Member member){
        return MemberDto.builder()
                .mbrNo(member.getMbrNo())
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}
