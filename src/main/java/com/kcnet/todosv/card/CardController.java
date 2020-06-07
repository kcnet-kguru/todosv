package com.kcnet.todosv.card;

import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cards", produces = MediaTypes.HAL_JSON_VALUE)
public class CardController {

    @PostMapping()
    public void create() {

    }

    @GetMapping("/{cardId}")
    public void get() {

    }

    @PutMapping("/{cardId}")
    public void update() {

    }

    @DeleteMapping("/{cardId}")
    public void delete() {

    }
}
