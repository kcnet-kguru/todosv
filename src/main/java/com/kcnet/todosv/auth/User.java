package com.kcnet.todosv.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class User {

  @Id
  private String email;
  private String password;

}
