package com.kodilla.crud.tasks.mapper;

import com.kodilla.crud.tasks.domain.Task;
import com.kodilla.crud.tasks.domain.TaskDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest
{
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTask()
    {
        //Given
        TaskDTO taskDTO = new TaskDTO(1L, "Task", "Testing mapping to Task");
        TaskDTO noArgsTaskDTO = new TaskDTO();
        //When
        Task task = taskMapper.mapToTask(taskDTO);
        Task noArgsTask = taskMapper.mapToTask(noArgsTaskDTO);
        //Then
        Assert.assertEquals(null, noArgsTask.getTaskID());
        Assert.assertEquals(null, noArgsTask.getTaskTitle());
        Assert.assertEquals(null, noArgsTask.getTaskContent());
        Assert.assertTrue(1L == task.getTaskID());
        Assert.assertEquals("Task", task.getTaskTitle());
        Assert.assertEquals("Testing mapping to Task", task.getTaskContent());
    }

    @Test
    public void mapToTaskDTO()
    {
        //Given
        Task task = new Task(1L, "TaskDTO", "Testing mapping to TaskDTO");
        //When
        TaskDTO taskDTO = taskMapper.mapToTaskDTO(task);
        //Then
        Assert.assertTrue(1L == taskDTO.getTaskID());
        Assert.assertEquals("TaskDTO", taskDTO.getTaskTitle());
        Assert.assertEquals("Testing mapping to TaskDTO", taskDTO.getTaskContent());
    }

    @Test
    public void mapToTaskDTOList()
    {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task1", "Testing mapping to list of TaskDTO's"));
        tasks.add(new Task(2L, "Task2", "Another testing mapping to list of TaskDTO's"));
        //When
        List<TaskDTO> taskDTOs = taskMapper.mapToTaskDTOList(tasks);
        //Then
        Assert.assertFalse(taskDTOs == null);
        Assert.assertEquals(2, taskDTOs.size());
        Assert.assertTrue(2L == taskDTOs.get(1).getTaskID());
        Assert.assertEquals("Task2", taskDTOs.get(1).getTaskTitle());
        Assert.assertEquals("Testing mapping to list of TaskDTO's", taskDTOs.get(0).getTaskContent());
    }
}