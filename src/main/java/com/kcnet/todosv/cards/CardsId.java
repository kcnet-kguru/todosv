package com.kcnet.todosv.cards;

import com.kcnet.todosv.lists.Lists;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardsId implements Serializable {


    @EqualsAndHashCode.Include
    @Id
    @Column(name = "board_id")
    private String boardId;

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "list_id")
    private String listId;

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "card_id")
    private String cardId;

}
