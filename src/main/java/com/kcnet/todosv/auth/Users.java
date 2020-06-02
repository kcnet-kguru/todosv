package com.kcnet.todosv.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Getter
@NoArgsConstructor
public class Users {

  @Id
  @Email
  private String email;

  @JsonIgnore
  private String password;

}
