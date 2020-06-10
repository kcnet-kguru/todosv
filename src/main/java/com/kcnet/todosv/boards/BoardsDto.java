package com.kcnet.todosv.boards;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardsDto {

    private String boardId;
    private String title;
    private String bgColor;

    public void setBgColor(String bgColor) {
        if (bgColor == null) bgColor = "rgb(0, 121, 191)";
        this.bgColor = bgColor;
    }
}
