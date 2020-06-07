package com.kcnet.todosv.cards;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Cards {

    @Id
    private String cardId;
    private String title;
    private String description;
    private int position;
}
