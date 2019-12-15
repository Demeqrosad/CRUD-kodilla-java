package com.kodilla.crud.tasks.mapper;

import com.kodilla.crud.tasks.domain.Task;
import com.kodilla.crud.tasks.domain.TaskDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper
{
    public Task mapToTask(final TaskDTO taskDTO)
    {
        return new Task(
                taskDTO.getTaskID(),
                taskDTO.getTaskTitle(),
                taskDTO.getTaskContent()
        );
    }

    public TaskDTO mapToTaskDTO(final Task task)
    {
        return new TaskDTO(
                task.getTaskID(),
                task.getTaskTitle(),
                task.getTaskContent()
        );
    }

    public List<TaskDTO> mapToTaskDTOList(final List<Task> taskList)
    {
        return taskList.stream()
                .map(t -> new TaskDTO(t.getTaskID(), t.getTaskTitle(), t.getTaskContent()))
                .collect(Collectors.toList());
    }
}
