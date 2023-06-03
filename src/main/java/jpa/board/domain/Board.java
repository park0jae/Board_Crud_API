package jpa.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jpa.board.domain.common.BaseEntity;
import jpa.board.dto.BoardRequestDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer; // 작성자
    private int hits;

    @Builder
    public Board(Long id, String title, String content, String writer , int hits){
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hits = hits;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


}
