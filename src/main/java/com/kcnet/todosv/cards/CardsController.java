package com.kcnet.todosv.cards;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        this.cardsRepository.save(modelMapper.map(dto, Cards.class));
        Link link = linkTo(methodOn(CardsController.class).getOne(dto.getCardId())).withSelfRel();
        return ResponseEntity.created(new URI(link.getHref())).build();
    }

    @PutMapping
    public ResponseEntity modify(@RequestBody CardsDto dto) throws URISyntaxException  {
        Cards cards = this.cardsRepository.save(modelMapper.map(dto, Cards.class));
        Link link = linkTo(methodOn(CardsController.class).getOne(dto.getCardId())).withSelfRel();
        EntityModel<Cards> entityModel = this.cardsResourceAssembler.toModel(cards);
        entityModel.add(link);
        return ResponseEntity.created(new URI(link.getHref())).body(entityModel);
    }

    @DeleteMapping("/{boardId}/{listId}/{cardId}")
    public ResponseEntity delete(@PathVariable String boardId, @PathVariable String listId, @PathVariable String cardId) {
        Cards card = new Cards(boardId, listId, cardId);
        this.cardsRepository.delete(card);
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
