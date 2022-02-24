package ex.springbbs.service;

import ex.springbbs.dto.BoardDto;
import ex.springbbs.entity.Board;
import ex.springbbs.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements IBoardService{
    private final IJwtService ijwtService;
    private final BoardRepository repository;

    @Autowired
    public BoardServiceImpl(IJwtService ijwtService, BoardRepository boardRepository) {
        this.ijwtService = ijwtService;
        this.repository = boardRepository;
    }

    @Override
    public BoardDto createBoard(@Validated BoardDto boardDto, String jwtToken) {
        boardDto.setWriter(ijwtService.authentication(jwtToken).getEmail());
        Board board = boardDto.toEntity();
        repository.saveAndFlush(board);
        return BoardDto.builder()
                .id(board.getId())
                .subject(board.getSubject())
                .content(board.getContent())
                .writer(board.getWriter())
                .build();
    }

    @Override
    public List<BoardDto> loadList(String jwtToken ) {
        ijwtService.authentication(jwtToken);
        List<Board> boardList = repository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board: boardList) {
            boardDtoList.add(BoardDto.
                    builder()
                    .id(board.getId())
                    .subject(board.getSubject())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .build()
            );
        }
        return boardDtoList;
    }
}