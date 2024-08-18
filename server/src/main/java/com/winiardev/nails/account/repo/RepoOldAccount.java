package com.winiardev.nails.account.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winiardev.nails.account.entity.OldAccount;

public interface RepoOldAccount extends JpaRepository<OldAccount, String> {
    List<OldAccount> findAllByEmail(String email);
}
