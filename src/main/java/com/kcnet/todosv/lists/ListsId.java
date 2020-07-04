package com.kcnet.todosv.lists;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListsId implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "board_id")
    private String boardId;

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "list_id")
    private String listId;
}
