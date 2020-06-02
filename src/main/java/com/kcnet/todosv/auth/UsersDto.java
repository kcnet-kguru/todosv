package com.kcnet.todosv.auth;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

  private String email;
  private String password;

}
