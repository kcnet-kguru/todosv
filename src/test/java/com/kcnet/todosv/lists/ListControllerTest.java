package com.kcnet.todosv.lists;

import com.kcnet.todosv.common.BaseControllerTest;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ListControllerTest extends BaseControllerTest {

    @Test
    public void createListTest() throws Exception {
        ListsDto listsDto = ListsDto.builder()
                            .boardId("B001")
                            .position(65535)
                            .title("test lists")
                            .build();
        mockMvc.perform(
                post("/lists")
                .contentType(MediaTypes.ALPS_JSON_VALUE)
                .accept(MediaTypes.HAL_JSON)
                .content(this.objectMapper.writeValueAsString(listsDto)))
           .andDo(print())
           .andExpect(status().isCreated());
    }

    @Test
    public void modifyLists() throws Exception {
        ListsDto listsDto = ListsDto.builder()
                .boardId("B001")
                .listId("L001")
                .position(65535)
                .title("test lists - modified")
                .build();

        mockMvc.perform(
                    put("/lists")
                    .contentType(MediaTypes.ALPS_JSON_VALUE)
                    .accept(MediaTypes.HAL_JSON)
                    .content(this.objectMapper.writeValueAsString(listsDto)))
                .andDo(print())
                .andExpect(status().isCreated());


    }

}
