package com.kcnet.todosv.auth;

import com.kcnet.todosv.board.BoardsController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsersResourceAssembler implements RepresentationModelAssembler<Users, EntityModel<Users>> {

    @Override
    public EntityModel<Users> toModel(Users users) {
        return new EntityModel<Users>(users,
                linkTo(methodOn(BoardsController.class).getAll()).withSelfRel());
    }
}
