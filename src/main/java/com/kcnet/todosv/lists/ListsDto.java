package com.kcnet.todosv.lists;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListsDto {

    private String boardId;
    private String listId;
    private String title;
    private int position;

    public void setPosition(int position) {
        if( position == 0) position = 65535;
        this.position = position;
    }
}
