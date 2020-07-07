package com.kcnet.todosv.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kcnet.todosv.audit.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(CardsId.class)
@Getter @Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = {  "listId", "createdAt", "updatedAt" })
public class Cards extends BaseTimeEntity {

    public Cards(String boardId, String listId, String cardId) {
        this.boardId = boardId;
        this.listId = listId;
        this.cardId = cardId;
    }

    @Id
    @Column(name="board_id")
    private String boardId;

    @Id
    @Column(name = "list_id")
    private String listId;

    @Id
    private String cardId;

    private String title;
    private String description;
    private int position;

}
