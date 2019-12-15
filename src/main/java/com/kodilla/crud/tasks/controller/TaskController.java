package com.kodilla.crud.tasks.controller;

import com.kodilla.crud.tasks.domain.TaskDTO;
import com.kodilla.crud.tasks.mapper.TaskMapper;
import com.kodilla.crud.tasks.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController
{
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private DBService service;

    @RequestMapping(method = RequestMethod.GET, value = "/getTasks")
    public List<TaskDTO> getTasks()
    {
        return taskMapper.mapToTaskDTOList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTask")
    public TaskDTO getTask(Long taskID)
    {
        return taskMapper.mapToTaskDTO(service.getTaskByTaskID(taskID));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteTask")
    public void deleteTask(Long taskID)
    {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateTask")
    public TaskDTO updateTask(TaskDTO taskDTO)
    {
        return new TaskDTO(1L, "Edited test title", "Test content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createTask")
    public void createTask(TaskDTO taskDTO)
    {

    }
}