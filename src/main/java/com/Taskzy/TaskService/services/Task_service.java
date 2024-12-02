package com.Taskzy.TaskService.services;


import com.Taskzy.TaskService.Model.Task;
import com.Taskzy.TaskService.Model.TaskStatus;

import java.util.List;

public interface Task_service {
    Task createTask(Task task,String reqRole) throws Exception;

    Task getTaskById(Long id) throws  Exception;

    List<Task> getAllTask(TaskStatus status);

    Task updateTask(Long id, Task reqtask, Long userId) throws Exception;

    void deleteTask(Long id) throws  Exception;

    Task assignedToUser(Long userId,Long taskId) throws Exception;

    List<Task> assignedUserTask(Long userId,TaskStatus status) throws  Exception;

    Task compeleteTask(Long taskId) throws Exception;
}
