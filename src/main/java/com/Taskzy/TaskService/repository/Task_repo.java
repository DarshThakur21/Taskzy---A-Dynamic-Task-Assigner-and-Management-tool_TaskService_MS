package com.Taskzy.TaskService.repository;

import com.Taskzy.TaskService.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Task_repo extends JpaRepository<Task,Long> {

    public List<Task> findByAssignedUserId(Long userId);

}
