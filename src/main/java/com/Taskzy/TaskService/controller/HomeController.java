package com.Taskzy.TaskService.controller;

import com.Taskzy.TaskService.Model.Task;
import com.Taskzy.TaskService.Model.TaskStatus;
import com.Taskzy.TaskService.Model.UserDto;
import com.Taskzy.TaskService.services.Task_service;
import com.Taskzy.TaskService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private Task_service taskService;

    @GetMapping("/tasks")
    public ResponseEntity<String> getAssignedUsersTask() {


        return new ResponseEntity<>("welcome to task service", HttpStatus.OK);

    }
}
