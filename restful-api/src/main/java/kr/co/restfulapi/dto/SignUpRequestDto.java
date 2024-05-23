package kr.co.restfulapi.dto;

import kr.co.restfulapi.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    public Member toEntity(){
        return Member.builder()
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .build();
    }
}

