package com.kcnet.todosv.list;

import com.kcnet.todosv.audit.BaseTimeEntity;
import com.kcnet.todosv.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lists extends BaseTimeEntity {

  @Id
  private String listId;
  private String title;
  private double position;

}
