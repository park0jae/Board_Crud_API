package jpa.board.controller;

import jpa.board.dto.BoardRequestDto;
import jpa.board.dto.BoardResponseDto;
import jpa.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // Board add Form 불러오기
    @GetMapping("/add")
    public String boardForm(){
        return "/board/write";
    }

    // Board 저장하기
    @PostMapping("/add")
    public String saveBoard(@ModelAttribute BoardRequestDto boardRequestDto) {
        boardService.save(boardRequestDto);
        log.info("title ={}", boardRequestDto.getTitle());
        log.info("contents= {}", boardRequestDto.getContent());

        return "redirect:/board/boards";
    }

    // Board List 불러오기
    @GetMapping("/boards")
    public String boardList(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("boards", boardService.findAll(pageable));
        return "/board/boards";
    }

    // Board Update Form 불러오기
    @GetMapping("/edit/{boardId}")
    public String editForm(@PathVariable("boardId") Long id, Model model){
        BoardResponseDto board = boardService.findById(id);
        model.addAttribute("board", board);

        return "board/editForm";
    }

    // Board Update
    @PostMapping("/edit/{boardId}")
    public String editBoard(@PathVariable Long boardId, @ModelAttribute BoardRequestDto boardRequestDto){
        boardService.update(boardId,boardRequestDto);
        return "redirect:/board/boards";
    }

    // Board 상세 페이지
    @GetMapping("/board/{boardId}")
    public String boardPage(@PathVariable Long boardId , Model model){
        BoardResponseDto board = boardService.findById(boardId);
        model.addAttribute("board",board);
        return "board/board";
    }

    // Board delete
    @DeleteMapping("/board/{boardId}")
    public String deleteBoard(@PathVariable Long boardId){
        boardService.delete(boardId);
        log.info("boardId={}", boardId);
        return "redirect:/board/boards";
    }
}

