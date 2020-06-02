package com.kcnet.todosv.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter @Setter
public class BoardDto extends RepresentationModel<BoardDto> {
    private long boardId;
    private String title;
    private String bgColor;
}
