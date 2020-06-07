package com.kcnet.todosv.board;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/boards", produces = MediaTypes.HAL_JSON_VALUE)
public class BoardsController {

    private final BoardsRepository boardsRepository;
    private final ModelMapper modelMapper;

    public BoardsController(BoardsRepository boardsRepository, ModelMapper modelMapper) {
        this.boardsRepository = boardsRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(boardsRepository.findAll());
    }
}
