package jpa.board.dto;

import jakarta.validation.constraints.NotBlank;
import jpa.board.domain.Board;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {

    private String title;
    private String content;
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
