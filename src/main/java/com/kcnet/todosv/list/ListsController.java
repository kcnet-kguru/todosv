package com.kcnet.todosv.list;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists", produces = MediaTypes.HAL_JSON_VALUE)
public class ListsController {

    private final ListsRepository listsRepository;
    private final ModelMapper modelMapper;

    public ListsController(ListsRepository listsRepository, ModelMapper modelMapper) {
        this.listsRepository = listsRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Lists>> query() {
        return new ResponseEntity<>(listsRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public void create() {

    }

    @PutMapping("/{listId}")
    public void update(@PathVariable String listId) {

    }

    @DeleteMapping("/{listId}")
    public void delete(@PathVariable String listId) {

    }

}
