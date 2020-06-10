package com.kcnet.todosv.lists;

import com.kcnet.todosv.boards.BoardsController;
import com.kcnet.todosv.boards.BoardsResourceAssembler;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/lists", produces = MediaTypes.HAL_JSON_VALUE)
public class ListsController {

    private final ListsRepository listsRepository;
    private final ModelMapper modelMapper;
    private final BoardsResourceAssembler boardsResourceAssembler;

    public ListsController(ListsRepository listsRepository, ModelMapper modelMapper, BoardsResourceAssembler boardsResourceAssembler) {
        this.listsRepository = listsRepository;
        this.modelMapper = modelMapper;
        this.boardsResourceAssembler = boardsResourceAssembler;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ListsDto dto) throws URISyntaxException {
        dto.setListId(generateListId());
        Lists list = modelMapper.map(dto, Lists.class);
        this.listsRepository.save(list);
        Link link = linkTo(methodOn(BoardsController.class).get(dto.getBoardId())).withSelfRel();
        EntityModel<Lists> entityModel = new EntityModel<>(list);
        entityModel.add(link);
        return ResponseEntity.created(new URI(link.getHref())).body(entityModel);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ListsDto dto) throws URISyntaxException {
        Lists list = modelMapper.map(dto, Lists.class);
        this.listsRepository.save(list);
        Link link = linkTo(methodOn(BoardsController.class).get(dto.getBoardId())).withSelfRel();
        EntityModel<Lists> entityModel = new EntityModel<>(list);
        entityModel.add(link);
        return ResponseEntity.created(new URI(link.getHref())).body(entityModel);

    }

    @DeleteMapping("/{listId}")
    public ResponseEntity delete(@PathVariable String listId) {
        this.listsRepository.deleteById(listId);
        return ResponseEntity.ok().build();
    }

    private String generateListId() {
        String nextId = "L001";
        Optional<Lists> lastListOptional = this.listsRepository.findFirstByOrderByCreatedAtDesc();
        if(lastListOptional.isPresent()) {
            nextId = "L" + String.format("%03d", Integer.parseInt(lastListOptional.get().getListId().replace("B", "")) + 1);
        }
        return nextId;
    }

}
