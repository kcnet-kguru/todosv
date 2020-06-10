package com.kcnet.todosv.cards;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CardsResourceAssembler implements RepresentationModelAssembler<Cards, EntityModel<Cards>> {

    @Override
    public EntityModel<Cards> toModel(Cards cards) {
        return new EntityModel<>(cards,
                linkTo(methodOn(CardsController.class).getOne(cards.getCardId())).withSelfRel());
    }
}
