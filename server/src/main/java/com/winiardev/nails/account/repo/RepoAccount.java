package com.winiardev.nails.account.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.account.entity.Account;

@Repository
public interface RepoAccount extends JpaRepository<Account, Email> {
    Optional<Account> findByEmail(Email email);
}
