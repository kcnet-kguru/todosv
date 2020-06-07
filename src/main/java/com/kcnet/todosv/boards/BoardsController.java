package com.kcnet.todosv.boards;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{boardId}")
    public ResponseEntity get(@PathVariable String boardId) {

        return null;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BoardsDto dto) {


        return null;
    }

    @PutMapping("/{boardId}")
    public ResponseEntity modify(@PathVariable String boardId, @RequestBody BoardsDto dto) {
        return null;
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity delete(@PathVariable String boardId) {
        return null;
    }
}
