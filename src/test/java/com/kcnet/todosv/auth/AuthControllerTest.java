package com.kcnet.todosv.auth;

import com.kcnet.todosv.common.BaseControllerTest;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTest extends BaseControllerTest {

    @Test
    public void signInNotFoundTest() throws Exception {
       UsersDto dto = UsersDto.builder()
                .email("123@kcnet.co.kr")
                .password("123123")
                .build();

       mockMvc.perform(post("/auth/")
                        .contentType(MediaTypes.HAL_JSON)
                        .content(this.objectMapper.writeValueAsString(dto)))
               .andDo(print())
               .andExpect(status().isNotFound());
    }

}
