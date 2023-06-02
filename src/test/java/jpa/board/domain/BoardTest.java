package jpa.board.domain;

import jpa.board.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardTest {

    @Autowired
    BoardRepository boardRepository;


    @AfterEach
    void afterEach(){
        boardRepository.deleteAll();
    }

    @Test
    void save(){
        // 1. 게시글 파라미터 생성
        Board params = createBoard("1번 게시글 제목", "1번 게시글 내용", "영재");

        // 2. 게시글 저장
        boardRepository.save(params);

        // 3. 1번 게시글 정보 조회
        Board board = boardRepository.findById(params.getId()).get();
        assertThat(board.getTitle()).isEqualTo("1번 게시글 제목");
        assertThat(board.getContent()).isEqualTo("1번 게시글 내용");
        assertThat(board.getHits()).isEqualTo(0);
    }

    @Test
    void findAll() {
        Board params = createBoard("1번 게시글 제목", "1번 게시글 내용", "영재");

        Board params2 = createBoard("2번 게시글 제목", "2번 게시글 내용", "영재");

        boardRepository.save(params);
        boardRepository.save(params2);

        // 1. 전체 게시글 수 조회
        long boardsCount = boardRepository.count();

        // 2. 전체 게시글 리스트 조회
        List<Board> boards = boardRepository.findAll();

        assertThat(boardsCount).isEqualTo(2);
        assertThat(boards.size()).isEqualTo(2);
    }

    @Test
    void delete() {
        Board board = createBoard("1번 게시글 제목", "1번 게시글 내용", "영재");

        boardRepository.save(board);

        // 1. 게시글 조회
        Board findBoard = boardRepository.findById(board.getId()).get();

        // 2. 게시글 삭제
        boardRepository.delete(findBoard);

        assertThat(boardRepository.count()).isEqualTo(0);
    }
    private static Board createBoard(String title, String content, String writer) {
        Board params = Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();

        return params;
    }

}