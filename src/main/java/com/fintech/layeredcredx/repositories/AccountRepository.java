package com.fintech.layeredcredx.repositories;

import com.fintech.layeredcredx.entities.Account;
import com.fintech.layeredcredx.entities.QAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID>, QuerydslPredicateExecutor<Account>, QuerydslBinderCustomizer<QAccount> {
}
