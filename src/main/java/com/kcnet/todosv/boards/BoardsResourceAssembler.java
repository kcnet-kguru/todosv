package com.kcnet.todosv.boards;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public class BoardsResourceAssembler implements RepresentationModelAssembler<Boards, EntityModel<Boards>> {

    @Override
    public EntityModel<Boards> toModel(Boards boards) {
        return new EntityModel<>(boards);
    }
}
