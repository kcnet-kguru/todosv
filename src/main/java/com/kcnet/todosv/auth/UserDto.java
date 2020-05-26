package com.kcnet.todosv.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDto {

  private String email;
  private String password;

}
