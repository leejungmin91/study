package kr.co.restfulapi.dto;

import kr.co.restfulapi.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    @NotBlank(message = "id는 필수입니다.")
    private String id;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    public Member toEntity(){
        return Member.builder()
                .id(this.id)
                .name(this.name)
                .password(this.password)
                .build();
    }
}

