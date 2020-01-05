package com.kodilla.crud.tasks.repository;

import com.kodilla.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long>
{
    @Override
    public List<Task> findAll();

    public Task findByTaskID(Long taskID);

    @Override
    public Task save(Task task);

    @Override
    public Optional<Task> findById (Long taskID);

    @Override
    public void deleteById (Long taskId);
}

