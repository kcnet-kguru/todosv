package com.kcnet.todosv.cards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardsRepository extends JpaRepository<Cards, String> {
    Optional<Cards> findFirstByOrderByCreatedAtDesc();
}
