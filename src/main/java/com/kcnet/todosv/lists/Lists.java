package com.kcnet.todosv.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kcnet.todosv.audit.BaseTimeEntity;
import com.kcnet.todosv.cards.Cards;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@IdClass(ListsId.class)
@Getter @Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" })
public class Lists extends BaseTimeEntity {

  public Lists(String boardId, String listId) {
    this.boardId = boardId;
    this.listId = listId;
  }

  public Lists(String boardId, String listId, String title, int position) {
    this.boardId = boardId;
    this.listId = listId;
    this.title = title;
    this.position = position;
  }

  @Id
  @Column(name = "board_id")
  private String boardId;

  @Id
  @Column(name = "list_id")
  private String listId;

  private String title;
  private int position;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumns(value = {
          @JoinColumn(name="list_id", updatable = false, insertable = false),
          @JoinColumn(name="board_id", updatable = false, insertable = false)
    }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
  private List<Cards> cards = new ArrayList<>();


}
