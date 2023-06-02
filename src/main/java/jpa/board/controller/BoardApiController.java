//package jpa.board.controller;
//
//import jpa.board.domain.Board;
//import jpa.board.dto.BoardRequestDto;
//import jpa.board.dto.BoardResponseDto;
//import jpa.board.exception.CustomException;
//import jpa.board.exception.ErrorCode;
//import jpa.board.service.BoardService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@Slf4j
//@RestController
//@RequestMapping("/api")
//@RequiredArgsConstructor
//public class BoardApiController {
//
//    private final BoardService boardService;
//
//    /**
//     * 게시글 생성
//     */
//    @PostMapping("/boards")
//    public Long save(@RequestBody final BoardRequestDto dto){
//        return boardService.save(dto);
//    }
//
//
//    /**
//     * 게시글 리스트 조회
//     */
//    @GetMapping("/boards")
//    public List<BoardResponseDto> findAll(){
//        return boardService.findAll();
//    }
//
//    /**
//     * 게시글 수정
//     */
//    @PatchMapping("/boards/{id}")
//    public Long save(@PathVariable final Long id, @RequestBody final BoardRequestDto dto){
//        return boardService.update(id, dto);
//    }
//
//
//}
