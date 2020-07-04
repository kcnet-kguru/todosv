package com.kcnet.todosv.cards;

import com.kcnet.todosv.common.BaseControllerTest;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CardControllerTest extends BaseControllerTest {

    @Test
    public void createCardsTest() throws Exception {
        CardsDto dto = CardsDto.builder()
                            .boardId("B001")
                            .listId("L001")
                            .position(65536)
                            .title("test card")
                            .description("테스트 카드 입니다.")
                            .build();
        mockMvc.perform(
                post("/cards")
                    .contentType(MediaTypes.ALPS_JSON_VALUE)
                    .accept(MediaTypes.HAL_JSON)
                    .content(this.objectMapper.writeValueAsString(dto)))
          .andDo(print())
          .andExpect(status().isCreated());
    }

    @Test
    public void modifyCards() throws Exception {
        CardsDto dto = CardsDto.builder()
                .boardId("B001")
                .listId("L001")
                .cardId("C001")
                .position(65536)
                .title("test card modified")
                .description("테스트 카드 내용이 수정 되었습니다.")
                .build();

        mockMvc.perform(
                put("/cards")
                        .contentType(MediaTypes.ALPS_JSON_VALUE)
                        .accept(MediaTypes.HAL_JSON)
                        .content(this.objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteCard() throws Exception {
        mockMvc.perform(
                delete("/cards/B001/L001/C001")
                        .contentType(MediaTypes.ALPS_JSON_VALUE)
                        .accept(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
