package jpa.board.service;

import jpa.board.domain.Board;
import jpa.board.dto.BoardRequestDto;
import jpa.board.dto.BoardResponseDto;
import jpa.board.exception.CustomException;
import jpa.board.exception.ErrorCode;
import jpa.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(BoardRequestDto boardRequestDto){
        Board board = boardRepository.save(boardRequestDto.toEntity());
        return board.getId();
    }

    /**
     * 게시글 리스트 조회
     */
    public Page<BoardResponseDto> findAll(Pageable pageable){
        Page<Board> page = boardRepository.findAll(pageable);
        return page.map(b -> BoardResponseDto.toDto(b));
    }
    public BoardResponseDto findById(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        return new BoardResponseDto(board);
    }
    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(final Long id, final BoardRequestDto boardRequestDto){
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        board.update(boardRequestDto.getTitle(), boardRequestDto.getContent());
        return board.getId();
    }
    @Transactional
    public void delete(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        boardRepository.deleteById(board.getId());
    }
}
