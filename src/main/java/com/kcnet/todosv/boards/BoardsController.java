package com.kcnet.todosv.boards;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(value = "/boards", produces = MediaTypes.HAL_JSON_VALUE)
public class BoardsController {

    private final BoardsRepository boardsRepository;
    private final BoardsResourceAssembler boardsResourceAssembler;
    private final ModelMapper modelMapper;

    public BoardsController(BoardsRepository boardsRepository, BoardsResourceAssembler boardsResourceAssembler, ModelMapper modelMapper) {
        this.boardsRepository = boardsRepository;
        this.boardsResourceAssembler = boardsResourceAssembler;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(boardsRepository.findAll());
    }

    @GetMapping("/{boardId}")
    public ResponseEntity get(@PathVariable String boardId) {
        return ResponseEntity.ok(boardsRepository.findById(boardId));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BoardsDto dto) throws URISyntaxException {
        dto.setBoardId(generateBoardId());
        Boards board = modelMapper.map(dto, Boards.class);
        this.boardsRepository.save(board);
        EntityModel<Boards> entityModel = this.boardsResourceAssembler.toModel(board);
        return ResponseEntity.created(new URI(linkTo(methodOn(this.getClass()).get(board.getBoardId())).withSelfRel().getHref())).body(entityModel);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity modify(@RequestBody BoardsDto dto) throws URISyntaxException {
        Boards board = modelMapper.map(dto, Boards.class);
        this.boardsRepository.save(board);
        EntityModel<Boards> entityModel = this.boardsResourceAssembler.toModel(board);
        return ResponseEntity.created(new URI(linkTo(methodOn(this.getClass()).get(board.getBoardId())).withSelfRel().getHref())).body(entityModel);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity delete(@PathVariable String boardId) {
        this.boardsRepository.deleteById(boardId);
        return ResponseEntity.ok().build();
    }


    private String generateBoardId() {
        String nextId = "B001";
        Optional<Boards> lastBoardOptional = this.boardsRepository.findFirstByOrderByCreatedAtDesc();
        if(lastBoardOptional.isPresent()) {
            nextId = "B" + String.format("%03d", Integer.parseInt(lastBoardOptional.get().getBoardId().replace("B", "")) + 1);
        }
        return nextId;
    }
}
