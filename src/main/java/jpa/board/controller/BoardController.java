package jpa.board.controller;

import jakarta.validation.Valid;
import jpa.board.dto.BoardRequestDto;
import jpa.board.dto.BoardResponseDto;
import jpa.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // Board add Form 불러오기
    @GetMapping("/add")
    public String boardForm(@ModelAttribute BoardRequestDto boardRequestDto, Model model){
        model.addAttribute("boardDto", boardRequestDto);
        return "/board/write";
    }

    // Board 저장하기
    @PostMapping("/add")
    public String saveBoard(@Valid @ModelAttribute BoardRequestDto boardRequestDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("boardDto", boardRequestDto);

            /* 유효성 검사를 통과하지 못 한 필드와 메시지 핸들링 */
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                log.info("filed={}, message={}  ", error.getField(), error.getDefaultMessage());

                model.addAttribute("error",errorMap);
            }

            return "/board/write";
        }

        boardService.save(boardRequestDto);
        return "redirect:/board/boards";
    }

    // Board List 불러오기
    @GetMapping("/boards")
    public String boardList(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        Page<BoardResponseDto> boardList = boardService.findAll(pageable);

        // 1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 함
        int nowPage = boardList.getPageable().getPageNumber() + 1;
        // -1 값이 들어가는 것을 막기 위해 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = boardList.getTotalPages();

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
    public String editBoard(@PathVariable Long boardId, @Valid @ModelAttribute BoardRequestDto boardRequestDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/board/editForm";
        }

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

