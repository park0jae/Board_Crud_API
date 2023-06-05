package jpa.board.repository;

import jpa.board.domain.Board;
import jpa.board.dto.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContaining(String keyword, Pageable pageable);
    Page<Board> findByWriterContaining(String keyword, Pageable pageable);
}
