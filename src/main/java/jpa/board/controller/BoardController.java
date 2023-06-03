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
    public String boardList(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        Page<BoardResponseDto> boardList = boardService.findAll(pageable);

        // 페이지블럭 처리
        // 1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 함
        int nowPage = boardList.getPageable().getPageNumber() + 1;
        // -1 값이 들어가는 것을 막기 위해 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 9, boardList.getTotalPages());

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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

