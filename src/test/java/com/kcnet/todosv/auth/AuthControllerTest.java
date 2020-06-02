package com.kcnet.todosv.auth;

import com.kcnet.todosv.common.BaseControllerTest;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthControllerTest extends BaseControllerTest {

  @Test
  public void login() throws Exception{

    UsersDto dto = UsersDto.builder()
                    .email("jsi123@kcnet.co.kr")
                    .password("12341234")
                    .build();

    mockMvc.perform(
              post("/auth/login")
              .contentType(MediaTypes.HAL_JSON_VALUE)
              .accept(MediaTypes.HAL_JSON)
              .content(this.objectMapper.writeValueAsString(dto))
            )
            .andDo(print())
            .andExpect(status().isOk());
  }
}
