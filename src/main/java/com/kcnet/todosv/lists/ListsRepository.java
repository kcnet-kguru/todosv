package com.kcnet.todosv.lists;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListsRepository extends JpaRepository<Lists, String> {
    Optional<Lists> findFirstByOrderByCreatedAtDesc();
}
