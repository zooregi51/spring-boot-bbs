package ex.springbbs.dto;

import ex.springbbs.entity.Member;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    @Pattern(regexp = "^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$")
    private String email;
    @Pattern(regexp = "^[0-9|a-z|A-Z]*$")
    private String password;
    @Pattern(regexp = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$")
    private String nickname;

    public Member toEntity() { // dto -> entity 변환해주는 책임
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}