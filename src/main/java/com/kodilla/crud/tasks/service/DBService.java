package com.kodilla.crud.tasks.service;

import com.kodilla.crud.tasks.domain.Task;
import com.kodilla.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService
{
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks()
    {
        return repository.findAll();
    }

    public Task getTaskByTaskID(Long taskID)
    {
        return repository.findByTaskID(taskID);
    }
}
