package com.kcnet.todosv.boards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardsRepository extends JpaRepository<Boards, String> {

    Optional<Boards> findFirstByOrderByCreatedAtDesc();

}
