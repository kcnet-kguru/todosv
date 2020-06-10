package com.kcnet.todosv.lists;

import com.kcnet.todosv.audit.BaseTimeEntity;
import com.kcnet.todosv.cards.Cards;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lists extends BaseTimeEntity {

  @Id
  private String listId;
  private String title;
  private int position;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "list_id", insertable = false, updatable = false)
  List<Cards> cards;
}
