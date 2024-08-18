package com.winiardev.nails.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winiardev.nails.task.entity.OldTask;

public interface RepoOldTask extends JpaRepository<OldTask, String> {

}
