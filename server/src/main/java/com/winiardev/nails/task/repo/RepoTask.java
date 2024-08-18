package com.winiardev.nails.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winiardev.nails.task.entity.Task;

@Repository
public interface RepoTask extends JpaRepository<Task, String> {

}
