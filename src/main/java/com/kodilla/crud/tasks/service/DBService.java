package com.kodilla.crud.tasks.service;

import com.kodilla.crud.tasks.domain.Task;
import com.kodilla.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Task> getTask(final Long taskID)
    {
        return repository.findById(taskID);
    }

    public Task saveTask(final Task task)
    {
        return repository.save(task);
    }

    public void deleteTask(final Long taskID)
    {
        repository.deleteById(taskID);
    }
}
