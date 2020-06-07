package com.kcnet.todosv.cards;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardsController {

    @GetMapping("/{cardId}")
    public ResponseEntity getOne(@PathVariable String cardId) {
        return null;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CardsDto dto) {
        return null;
    }

    @PutMapping("/{cardId}")
    public ResponseEntity modify(@PathVariable String cardId, @RequestBody CardsDto dto) {
        return null;
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity delete(@PathVariable String cardId) {
        return null;
    }

}
