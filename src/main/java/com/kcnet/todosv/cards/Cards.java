package com.kcnet.todosv.cards;

import com.kcnet.todosv.audit.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Cards extends BaseTimeEntity {

    @Id
    private String cardId;
    private String title;
    private String description;
    private int position;

}
