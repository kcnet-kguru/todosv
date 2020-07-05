package com.kcnet.todosv.boards;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(value = "/boards", produces = MediaTypes.HAL_JSON_VALUE)
public class BoardsController {

    private BoardsService boardsService;
    private BoardsResourceAssembler boardsResourceAssembler;

    public BoardsController(BoardsService boardsService, BoardsResourceAssembler boardsResourceAssembler) {
        this.boardsService = boardsService;
        this.boardsResourceAssembler = boardsResourceAssembler;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(this.boardsService.fetchAll());
    }

    @GetMapping("/{boardId}")
    public ResponseEntity get(@PathVariable String boardId) {
        return ResponseEntity.ok(this.boardsService.fetchBoard(boardId));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BoardsDto dto) throws URISyntaxException {
        Boards board = this.boardsService.createBoard(dto);
        EntityModel<Boards> entityModel = this.boardsResourceAssembler.toModel(board);
        return ResponseEntity.created(new URI(linkTo(methodOn(this.getClass()).get(board.getBoardId())).withSelfRel().getHref())).body(entityModel);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity modify(@PathVariable String boardId, @RequestBody BoardsDto dto) throws URISyntaxException {
        dto.setBoardId(boardId);
        Boards board = this.boardsService.modifyBoard(dto);
        EntityModel<Boards> entityModel = this.boardsResourceAssembler.toModel(board);
        return ResponseEntity.created(new URI(linkTo(methodOn(this.getClass()).get(board.getBoardId())).withSelfRel().getHref())).body(entityModel);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity delete(@PathVariable String boardId) {
        this.boardsService.deleteBoard(boardId);
        return ResponseEntity.ok().build();
    }
}
