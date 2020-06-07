package com.kcnet.todosv.board;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/boards", produces = MediaTypes.HAL_JSON_VALUE)
public class BoardController {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    public BoardController(BoardRepository boardRepository, ModelMapper modelMapper) {
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Board>> query() {
        return new ResponseEntity<>(this.boardRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BoardDto dto, Errors errors) {
        if(errors.hasErrors()) return ResponseEntity.badRequest().body(errors);
        boardRepository.save(modelMapper.map(dto, Board.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{boardId}")
    public void getBoard(@PathVariable String boardId) {
    }

    @PutMapping("/{boardId}")
    public void update(@PathVariable String boardId) {

    }

    @DeleteMapping("/{boardId}")
    public void delete(@PathVariable String boardId) {

    }
}
