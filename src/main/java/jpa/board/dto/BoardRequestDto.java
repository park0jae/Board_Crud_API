package jpa.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jpa.board.domain.Board;
import lombok.*;


@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    @Size(min = 2, max = 50)
    private String content;

    @NotEmpty(message = "작성자를 입력해주세요.")
    private String writer;

    @Builder
    public BoardRequestDto(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    // request dto 로 받은 Posts 객체를 entity 화하여 저장하는 용도
    public Board toEntity() {
        return Board.builder().title(title).content(content).writer(writer).hits(0).build();
    }

}
