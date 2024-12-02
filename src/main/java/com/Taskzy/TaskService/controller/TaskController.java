package com.Taskzy.TaskService.controller;


import com.Taskzy.TaskService.Model.Task;
import com.Taskzy.TaskService.Model.TaskStatus;
import com.Taskzy.TaskService.Model.UserDto;
import com.Taskzy.TaskService.services.Task_service;
import com.Taskzy.TaskService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private UserService userService;

    @Autowired
    private Task_service taskService;

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);


        Task createdTask = taskService.createTask(task, userDto.getRole());

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto userDto = userService.getUserProfile(jwt);

        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUsersTask(@RequestParam(required = false) TaskStatus status
            , @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto userDto = userService.getUserProfile(jwt);
        List<Task> tasks = taskService.assignedUserTask(userDto.getId(), status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) TaskStatus status
            , @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto userDto = userService.getUserProfile(jwt);
        List<Task> tasks = taskService.getAllTask(status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }


    @PutMapping("/{taskId}/user/{userId}/assigned")
    public ResponseEntity<Task> assignedTaskToUSer(@PathVariable Long taskId, @PathVariable Long userId
            , @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto userDto = userService.getUserProfile(jwt);

        Task task = taskService.assignedToUser(userId,taskId);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId,@RequestBody Task req
            , @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto userDto = userService.getUserProfile(jwt);

        Task task = taskService.updateTask(taskId,req,userDto.getId());

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long taskId) throws Exception {
//        UserDto userDto = userService.getUserProfile(jwt);

        Task task = taskService.compeleteTask(taskId);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) throws Exception {
//        UserDto userDto = userService.getUserProfile(jwt);

        taskService.deleteTask(taskId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }












}