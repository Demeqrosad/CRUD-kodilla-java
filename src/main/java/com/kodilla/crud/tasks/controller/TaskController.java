package com.kodilla.crud.tasks.controller;

import com.kodilla.crud.tasks.domain.TaskDTO;
import com.kodilla.crud.tasks.mapper.TaskMapper;
import com.kodilla.crud.tasks.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class TaskController
{
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private DBService service;

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public List<TaskDTO> getTasks()
    {
        return taskMapper.mapToTaskDTOList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskID}")
    public TaskDTO getTask(@PathVariable Long taskID) throws TaskNotFoundException
    {
        return taskMapper.mapToTaskDTO(service.getTask(taskID).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskID}")
    public void deleteTask(@PathVariable Long taskID)
    {
        service.deleteTask(taskID);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public TaskDTO updateTask(@RequestBody TaskDTO taskDTO)
    {
        return taskMapper.mapToTaskDTO(service.saveTask(taskMapper.mapToTask(taskDTO)));
    }


    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_UTF8_VALUE)
    public void createTask(@RequestBody TaskDTO taskDTO)
    {
        service.saveTask(taskMapper.mapToTask(taskDTO));
    }
}
