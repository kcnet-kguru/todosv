package com.kcnet.todosv.board;

import com.kcnet.todosv.audit.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

  @Id
  @GeneratedValue
  private int boardId;
  private String title;
  private String bgColor;

  public Board(String title, String bgColor) {
    this.title = title;
    this.bgColor = bgColor == null? "#026aa7" : bgColor;
  }


}
