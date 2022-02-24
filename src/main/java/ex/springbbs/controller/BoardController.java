package ex.springbbs.controller;

import ex.springbbs.dto.BoardDto;
import ex.springbbs.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {
    private final IBoardService boardService;

    @Autowired
    public BoardController(IBoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping(path = "/board") // 글쓰기
    public BoardDto writing(@RequestBody @Validated BoardDto boardDto, @RequestHeader("Authorization") String jwtToken) {
        return boardService.createBoard(boardDto, jwtToken);
    }

    @GetMapping("/board") // 리스트 가져오기
    public List<BoardDto> loadBoardList(@RequestHeader("Authorization") String jwtToken) {
        return boardService.loadList(jwtToken);
    }
}