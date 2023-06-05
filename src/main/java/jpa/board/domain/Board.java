package jpa.board.domain;

import jakarta.persistence.*;
import jpa.board.domain.common.BaseEntity;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
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
