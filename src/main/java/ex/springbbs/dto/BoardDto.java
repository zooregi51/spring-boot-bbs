package ex.springbbs.dto;

import ex.springbbs.entity.Board;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long id;
    @NotBlank(message = "글 제목이 없습니다.")
    private String subject;
    @NotBlank(message = "작성된 컨텐츠가 없습니다.")
    private String content;
    private String writer;

    public Board toEntity() { // dto -> entity 변환해주는 책임
        return Board.builder()
                .subject(subject)
                .content(content)
                .writer(writer)
                .build();
    }
}