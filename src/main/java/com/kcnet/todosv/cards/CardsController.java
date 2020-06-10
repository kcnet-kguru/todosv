package com.kcnet.todosv.cards;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardsController {

    private final CardsRepository cardsRepository;
    private final CardsResourceAssembler cardsResourceAssembler;
    private final ModelMapper modelMapper;

    public CardsController(CardsRepository cardsRepository, CardsResourceAssembler cardsResourceAssembler, ModelMapper modelMapper) {
        this.cardsRepository = cardsRepository;
        this.cardsResourceAssembler = cardsResourceAssembler;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{cardId}")
    public ResponseEntity getOne(@PathVariable String cardId) {
        Optional<Cards> card = this.cardsRepository.findById(cardId);
        if(card.isPresent()) return ResponseEntity.ok(card);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CardsDto dto) throws URISyntaxException {
        dto.setCardId(this.generateCardId());
        Cards cards = this.cardsRepository.save(modelMapper.map(dto, Cards.class));
        EntityModel<Cards> entityModel = this.cardsResourceAssembler.toModel(cards);
        return ResponseEntity.created(new URI(entityModel.getLink(cards.getCardId()).get().expand().getHref())).body(entityModel);
    }

    @PutMapping
    public ResponseEntity modify(@RequestBody CardsDto dto) throws URISyntaxException  {
        Cards cards = this.cardsRepository.save(modelMapper.map(dto, Cards.class));
        EntityModel<Cards> entityModel = this.cardsResourceAssembler.toModel(cards);
        return ResponseEntity.created(new URI(entityModel.getLink(cards.getCardId()).get().expand().getHref())).body(entityModel);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity delete(@PathVariable String cardId) {
        this.cardsRepository.deleteById(cardId);
        return ResponseEntity.ok().build();
    }

    private String generateCardId() {
        String nextId = "C001";
        Optional<Cards> lastCardOptional = this.cardsRepository.findFirstByOrderByCreatedAtDesc();
        if(lastCardOptional.isPresent()) {
            nextId = "C" + String.format("%03d", Integer.parseInt(lastCardOptional.get().getCardId().replace("C", "")) + 1);
        }
        return nextId;
    }
}
