package com.kodilla.crud.tasks.repository;

import com.kodilla.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long>
{
    @Override
    public List<Task> findAll();

    public Task findByTaskID(Long taskID);
}
