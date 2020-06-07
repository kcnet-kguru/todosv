package com.kcnet.todosv.lists;

import com.kcnet.todosv.audit.BaseTimeEntity;
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
