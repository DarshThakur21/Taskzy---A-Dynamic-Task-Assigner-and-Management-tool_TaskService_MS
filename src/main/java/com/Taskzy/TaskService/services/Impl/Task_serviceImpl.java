package com.Taskzy.TaskService.services.Impl;

import com.Taskzy.TaskService.Model.Task;
import com.Taskzy.TaskService.Model.TaskStatus;
import com.Taskzy.TaskService.repository.Task_repo;
import com.Taskzy.TaskService.services.Task_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Task_serviceImpl implements Task_service
{
    @Autowired
    private Task_repo taskRepo;

    @Override
    public Task createTask(Task task, String reqRole) throws Exception {
        if(!reqRole.equals(("ADMIN"))){
            throw  new Exception("only admin can create the tasks");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepo.save(task);

    }

    @Override
    public Task getTaskById(Long id) throws Exception {
            Task task=taskRepo.findById(id).orElseThrow(()->new Exception("task not found with id"+id));
        return task;
    }

    @Override
    public List<Task> getAllTask(TaskStatus status) {

        List<Task> allTasks=taskRepo.findAll();

//        it will return the list on the basis of task status and if the status is null so the all task are displayed
        List<Task> filterTasks= allTasks.stream().filter(
                task -> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());

        return filterTasks;
    }

    @Override
    public Task updateTask(Long id, Task reqtask, Long userId) throws Exception {
        Task existingTask=getTaskById(id);


        if(reqtask.getTitle()!=null){
            existingTask.setTitle(reqtask.getTitle());
        }
        if(reqtask.getDescription()!=null){
            existingTask.setDescription(reqtask.getDescription());
        }
        if(reqtask.getImage()!=null){
            existingTask.setImage(reqtask.getImage());
        }
        if(reqtask.getStatus()!=null){
            existingTask.setStatus(reqtask.getStatus());
        }
        if(reqtask.getDeadline()!=null){
            existingTask.setDeadline(reqtask.getDeadline());
        }

        return taskRepo.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) throws Exception {
       getTaskById(id);
       taskRepo.deleteById(id);

    }

    @Override
    public Task assignedToUser(Long userId, Long taskId) throws Exception {
        Task task=getTaskById(taskId);

        task.setAssignedUserId(userId);
        task.setStatus(TaskStatus.DONE);

        return taskRepo.save(task);
    }

    @Override
    public List<Task> assignedUserTask(Long userId, TaskStatus status) throws Exception {
            List<Task> allTasks=taskRepo.findByAssignedUserId(userId);
            List<Task> filteredTasks=allTasks.stream().filter(
                    task -> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
            ).collect(Collectors.toList());


        return filteredTasks;
    }

    @Override
    public Task compeleteTask(Long taskId) throws Exception {
        Task task=getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);

        return taskRepo.save(task);
    }
}
