package com.kcnet.todosv.boards;

import com.kcnet.todosv.common.BaseControllerTest;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BoardControllerTest extends BaseControllerTest {

    @Test
    public void createTest() throws Exception{
        BoardDto dto = BoardDto.builder()
                    .title("test")
                    .build();

        mockMvc.perform(
                post("/boards")
                        .contentType(MediaTypes.HAL_JSON_VALUE)
                        .accept(MediaTypes.HAL_JSON)
                        .content(this.objectMapper.writeValueAsString(dto))
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getQuery() throws Exception {
        mockMvc.perform(
                get("/boards")
                    .contentType(MediaTypes.HAL_JSON_VALUE)
                    .accept(MediaTypes.HAL_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
