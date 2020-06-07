package com.kcnet.todosv.board;

import com.kcnet.todosv.common.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Boards extends BaseTimeEntity {

    @Id
    private String boardId;
    private String title;
    private String bgColor;

}
