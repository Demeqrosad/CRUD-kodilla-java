package com.kodilla.crud.tasks.controller;

import com.kodilla.crud.tasks.domain.TaskDTO;
import com.kodilla.crud.tasks.mapper.TaskMapper;
import com.kodilla.crud.tasks.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

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
    public TaskDTO getTask(@RequestParam Long taskID) throws TaskNotFoundException
    {
        return taskMapper.mapToTaskDTO(service.getTask(taskID).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteTask")
    public void deleteTask(@RequestParam Long taskID)
    {
        service.deleteTask(taskID);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateTask")
    public TaskDTO updateTask(@RequestBody TaskDTO taskDTO)
    {
        return taskMapper.mapToTaskDTO(service.saveTask(taskMapper.mapToTask(taskDTO)));
    }


    @RequestMapping(method = RequestMethod.POST, value = "/createTask", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDTO taskDTO)
    {
        service.saveTask(taskMapper.mapToTask(taskDTO));
    }
}
