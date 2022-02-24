package ex.springbbs.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtDto {
    private String jwt;
    private String email;
}
