package com.kcnet.todosv.cards;


import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardsDto {

    private String boardId;
    private String listId;
    private String cardId;
    private String title;
    private String description;
    private int position;
}
