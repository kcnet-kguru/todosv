package com.kcnet.todosv.boards;

import com.kcnet.todosv.common.BaseTimeEntity;
import com.kcnet.todosv.lists.Lists;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Boards extends BaseTimeEntity {

    @Id
    private String boardId;
    private String title;
    private String bgColor;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id", insertable = false, updatable = false)
    List<Lists> lists;

}
