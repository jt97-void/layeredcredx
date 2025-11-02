package com.fintech.layeredcredx.repositories;

import com.fintech.layeredcredx.entities.Card;
import com.fintech.layeredcredx.entities.QCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID>, QuerydslPredicateExecutor<Card>, QuerydslBinderCustomizer<QCard> {
}
