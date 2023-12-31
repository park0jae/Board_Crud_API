package jpa.board.apicontroller;

import jpa.board.dto.BoardRequestDto;
import jpa.board.dto.BoardResponseDto;
import jpa.board.jsonservice.BoardApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardApiService boardApiService;

    /**
     * 게시글 생성
     */
    @PostMapping
    public String save(@RequestBody final BoardRequestDto dto){
        boardApiService.save(dto);
        return "저장 완료";
    }
    /**
     * 게시글 리스트 조회
     */
    @GetMapping
    public Page<BoardResponseDto> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size
        ,String searchKeyword, String searchValue
    ){
        PageRequest pageable = PageRequest.of(page, size);
        if(searchKeyword!= null ) {
            if (searchKeyword.equals("title")) {
                return boardApiService.searchTitle(searchValue, pageable);
            } else {
                return boardApiService.searchWriter(searchValue, pageable);
            }
        }else {
            return boardApiService.findAll(pageable);
        }
    }
    /**
     * 단건 조회
     */
    @GetMapping("/{boardId}")
    public BoardResponseDto findByIdBoard(@PathVariable final Long boardId){
        return boardApiService.findById(boardId);
    }
    /**
     * 게시글 수정
     */
    @PatchMapping("/{boardId}")
    public String save(@PathVariable final Long boardId, @RequestBody final BoardRequestDto dto){
        boardApiService.update(boardId, dto);
        return "수정 완료";
    }

    @DeleteMapping("/{boardId}")
    public String deleteBoard(@PathVariable  Long boardId){
        boardApiService.delete(boardId);
        return "삭제 완료";
    }


}
