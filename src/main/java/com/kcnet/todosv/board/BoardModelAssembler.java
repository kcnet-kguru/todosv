package com.kcnet.todosv.board;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class BoardModelAssembler extends RepresentationModelAssemblerSupport<Board, BoardDto> {

    public BoardModelAssembler() {
        super(BoardController.class, BoardDto.class);
    }

    @Override
    public BoardDto toModel(Board board) {
        return null;
    }
}
