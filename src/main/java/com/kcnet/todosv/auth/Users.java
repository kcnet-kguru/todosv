package com.kcnet.todosv.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kcnet.todosv.common.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Users extends BaseTimeEntity {

    @Id
    private String email;

    @JsonIgnore
    private String password;
}
