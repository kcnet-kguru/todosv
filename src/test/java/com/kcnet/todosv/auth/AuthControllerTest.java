package com.kcnet.todosv.auth;

import com.kcnet.todosv.common.BaseControllerTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthControllerTest extends BaseControllerTest {

  @Test
  public void authGet() throws Exception{
    mockMvc.perform(
        get("/auth")
    ).andDo(print())
     .andExpect(status().isOk());
  }
}
