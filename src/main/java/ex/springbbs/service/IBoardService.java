package ex.springbbs.service;

import ex.springbbs.dto.BoardDto;

import java.util.List;

public interface IBoardService {
    BoardDto createBoard(BoardDto boardDto, String jwtToken);
    List<BoardDto> loadList(String jwtToken);
}